package control;

import java.util.ArrayList;

import model.Rule;
import model.State;

public class RuleManager {
    
    // Attributes.
    
    private static RuleManager instance;
    private ArrayList<Rule> rules;
    
    // Methods.
    
    private RuleManager() {
        this.rules = new ArrayList<Rule>();
        this.rules.add(RuleFactory.getInstance().getRule("game_of_life"));
        this.rules.add(RuleFactory.getInstance().getRule("fredkin"));
    }
    
    public static RuleManager getInstance() {
        if (instance == null) instance = new RuleManager();
        return instance;
    }
    
    public String getRule(int id) {
        boolean found = false;
        int cursor = 0;
        while (!found && cursor < rules.size()) {
            if (rules.get(cursor).getId() == id) {
                found = true;
            }
            else cursor++;
        }
        if (found) return rules.get(cursor).getName();
        else throw new IllegalArgumentException("Get Rule: Rule number " + id + " does not exists.");
    }
    
    public Rule getRule(String rule_name) {
        boolean found = false;
        int cursor = 0;
        while (!found && cursor < rules.size()) {
            if (rules.get(cursor).getName() == rule_name) {
                found = true;
            }
            else cursor++;
        }
        if (found) return rules.get(cursor);
        else throw new IllegalArgumentException("Get Rule: Rule number " + rule_name + " does not exists.");
    }
    
    public String[] getRules() {
        String[] rules_names = new String[this.rules.size()];
        for (int i = 0; i < this.rules.size(); i++) {
            rules_names[i] = this.rules.get(i).getName();
        }
        return rules_names;
    }
}
