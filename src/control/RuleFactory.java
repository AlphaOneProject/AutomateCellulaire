package control;

import model.Neighborhood;
import model.Rule;
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
    
    public Rule getRule(String rule_type, State match_state, State[] results, int n_width, int n_height,
                        boolean[][] n_selected, int[] n_origin) {
        Rule new_rule = null;
        
        switch(rule_type) {
        case "unistate":
            Neighborhood neighborhood = new Neighborhood(n_width, n_height, n_selected, n_origin);
            new_rule = new UniState(match_state, results, neighborhood);
            break;
        default:
            throw new IllegalArgumentException("Get Rule: Invalid rule type provided: \"" + rule_type +
                                               "\"");
        }
        
        return new_rule;
    }
}
