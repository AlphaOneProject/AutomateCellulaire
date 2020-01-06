package model;

import java.util.ArrayList;

public class Grid {
    
    // Attributes.
    
    private int width;
    private int height;
    private Automaton[][] automata;
    
    // Methods.
    
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.automata = new Automaton[height][width];
        
        // Fill the grid with actual cells.
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                this.automata[i][j] = new Automaton(i, j, State.DEAD);
            }
        }
    }
    
    public Automaton[][] getAutomata() {
        return this.automata.clone();
    }
    
    public State[][] getStates() {
        State[][] states = new State[this.height][this.width];
        for(int i = 0; i < this.height; i++) {
            for(int j = 0; j < this.width; j++) {
                states[i][j] = this.automata[i][j].getState();
            }
        }
        return states;
    }
    
    public void setStates(State[][] new_states) {
        for(int i = 0; i < this.height; i++) {
            for(int j = 0; j < this.width; j++) {
                this.automata[i][j].setState(new_states[i][j]);
            }
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public Grid clone() {
        Grid clone = new Grid(this.width, this.height);
        clone.automata = this.automata.clone();
        return clone;
    }
}
