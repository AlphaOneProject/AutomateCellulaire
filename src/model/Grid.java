package model;

import java.util.ArrayList;

public class Grid {
    
    // Attributes.
    
    private int width;
    private int height;
    private Extension extension;
    private Automaton[][] automata;
    private Player owner;
    
    // Methods.
    
    public Grid(int width, int height, Extension extension, Player owner) {
        this.width = width;
        this.height = height;
        this.extension = extension;
        this.automata = new Automaton[height][width];
        this.owner = owner;
        
        // Fill the grid with actual cells.
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                this.automata[i][j] = new Automaton(i, j, State.DEAD);
            }
        }
    }
    
    public Automaton get(int x, int y) {
        return this.extension.get(this, x, y);
    }
    
    public Automaton[][] getAutomata() {
        return this.automata.clone();
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setExtension(Extension new_extension) {
        this.extension = new_extension;
    }
    
    public Player getOwner() {
        return this.owner;
    }
    
    public void nextTurn() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.automata[i][j].nextTurn(this.clone());
            }
        }
    }
    
    @Override
    public Grid clone() {
        Grid clone = new Grid(this.width, this.height, this.extension, this.owner);
        clone.automata = this.automata.clone();
        return clone;
    }
}
