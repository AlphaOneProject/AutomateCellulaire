package control;

import model.Neighborhood;
import model.Rule;
import model.SelfAwareUniState;
import model.UniState;
import model.State;

public class RuleFactory {
    
    // Attributes.
    
    private static RuleFactory instance;
    
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
            new_rule = new SelfAwareUniState("Jeu de la vie", State.ALIVE, new State[] {State.ALIVE, State.DEAD},
                                             new State[][] {
                                                            {State.DEAD, State.DEAD, State.DEAD, State.ALIVE, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
                                                            {State.DEAD, State.DEAD, State.ALIVE, State.ALIVE, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD}
                                                            },
                                             neighborhood);
            break;
        case "fredkin":
            new_rule = new UniState("Fredkin", State.ALIVE, new State[] {State.DEAD, State.ALIVE, State.ALIVE, State.ALIVE, State.DEAD},
                                    new Neighborhood());
            break;
        default:
            throw new IllegalArgumentException("Get Rule: Invalid rule type provided: \"" + rule_name +
                                               "\"");
        }
        
        return new_rule;
    }
}
