package control;

import java.util.ArrayList;

import model.Rule;

public class RuleManager {
    
    // Attributes.
    
    private static RuleManager instance;
    private ArrayList<Rule> rules;
    
    // Methods.
    
    private RuleManager() {
        this.rules = new ArrayList<Rule>();
    }
    
    public static RuleManager getInstance() {
        if (instance == null) instance = new RuleManager();
        return instance;
    }
}
