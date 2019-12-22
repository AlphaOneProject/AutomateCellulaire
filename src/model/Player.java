package model;

public class Player {
    
    // Attributes.
    
    private static int next_id = 0;
    private int id;
    private String name;
    private Rule rule;
    
    // Methods.
    
    public Player(String name) {
        this.id = next_id;
        this.name = name;
        
        next_id++;
    }
    
    public static int getNextId() {
        return next_id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Rule getRule() {
        return this.rule;
    }
    
    public int getScore(Grid grid) {
        // WIP: Compute score.
        
        return 0;
    }
}
