package control;

import model.ElementaryCellularAutomaton;
import model.Neighborhood;
import model.Rule;
import model.SelfAwareUniState;
import model.UniState;
import model.State;

public class RuleFactory {
    
    // Attributes.
    
    private static RuleFactory instance;
    private int next_id = 0;
    
    // Methods.
    
    private RuleFactory() {}
    
    public static RuleFactory getInstance() {
        if (instance == null) instance = new RuleFactory();
        return instance;
    }
    
    public Rule getRule(String rule_name) {
        Rule new_rule = null;
        
        switch(rule_name) {
        case "game_of_life":
            Neighborhood neighborhood = new Neighborhood(new boolean[][] {{true, true, true}, {true, false, true}, {true, true, true}},
                                                         new int[] {1, 1});
            new_rule = new SelfAwareUniState(this.next_id, "Jeu de la vie", State.ALIVE, new State[] {State.ALIVE, State.DEAD},
                                             new State[][] {
                                                            {State.DEAD, State.DEAD, State.DEAD, State.ALIVE, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
                                                            {State.DEAD, State.DEAD, State.ALIVE, State.ALIVE, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD}
                                                            },
                                             neighborhood);
            break;
        case "fredkin":
            new_rule = new UniState(this.next_id, "Fredkin", State.ALIVE, new State[] {State.DEAD, State.ALIVE, State.ALIVE, State.ALIVE, State.DEAD},
                                    new Neighborhood());
            break;
        default:
            throw new IllegalArgumentException("Get Rule: Invalid rule type provided: \"" + rule_name +
                                               "\"");
        }
        this.next_id++;
        
        return new_rule;
    }
    
    public Rule getRuleECA(int rule_number) {
        if (rule_number < 0 || rule_number > 255) {
            throw new IllegalArgumentException("Get Rule: Invalid rule number provided: \"" + rule_number + "\"");
        }
        
        Rule new_rule = new ElementaryCellularAutomaton(this.next_id, rule_number);
        this.next_id++;
        
        return new_rule;
    }
}
