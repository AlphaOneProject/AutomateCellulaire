package model;

import java.util.ArrayList;

public class Cell {
    
    // Attributes.
    
    private int x;
    private int y;
    
    // Methods.
    
    public Cell(int x, int y) {
        this.automata = new ArrayList<Automaton>();
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public ArrayList<Automaton> getAutomata() {
        return new ArrayList<Automaton>(this.automata);
    }
    
    public void addAutomaton(Automaton new_automaton) {
        this.automata.add(new_automaton);
    }
    
    public void emptyAutomata() {
        this.automata = new ArrayList<Automaton>();
    }
    
    public State getState() {
        if(this.automata.size() > 1) {
            throw new IllegalStateException("A Cell must contain only one Automaton while calling getState()" +
                                            "method and " + this.automata.size() + " were found.");
        }
        
        return this.automata.get(0).getState();
    }
}
