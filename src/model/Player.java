package model;

public class Player {
    
    // Attributes.
    
    private static int next_id = 0;
    private int id;
    private String name;
    private Grid grid;
    private Rule rule;
    
    // Methods.
    
    public Player(String name, int width, int height) {
        this.id = next_id;
        this.name = name;
        this.grid = new Grid(width, height);
        
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
    
    public Grid getGrid() {
        return this.grid;
    }
    
    public Rule getRule() {
        return this.rule;
    }
    
    public void setRule(Rule new_rule) {
        this.rule = new_rule;
    }
    
    public int getScore() {
        // WIP
        return -1;
    }
    
    public void nextTurn() {
        Grid previous_states = this.grid.clone();
        for (int i = 0; i < this.grid.getHeight(); i++) {
            for (int j = 0; j < this.grid.getWidth(); j++) {
                this.grid.getAutomata()[i][j].nextTurn(previous_states, this);
            }
        }
    }
}
