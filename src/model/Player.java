package model;

import control.Game;

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
        this.rule = new UniState();
        
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
    
    public void setGridSize(int new_width, int new_height) {
        this.grid = new Grid(new_width, new_height);
    }
    
    public Rule getRule() {
        return this.rule;
    }
    
    public void setRule(Rule new_rule) {
        this.rule = new_rule;
    }
    
    public int getScore() {
        int score = 0;
        for (int i = 0; i < this.grid.getWidth(); i++) {
            for (int j = 0; j < this.grid.getHeight(); j++) {
                if (Game.getInstance().getExtension().get(this.grid, i, j).getState() != State.DEAD) score++;
            }
        }
        return score;
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
