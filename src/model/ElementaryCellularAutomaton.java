package model;

import control.Game;

public class ElementaryCellularAutomaton implements Rule {
    
    // Attributes.
    
    private int id;
    private int number;
    private String name;

    // Methods.
    
    public ElementaryCellularAutomaton(int id, int number) {
        this.id = id;
        this.number = number;
        this.name = "ECA " + number;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    @Override
    public State applyRule(Grid grid, Automaton automaton) {
        State state = State.DEAD;
        return state;
    }
}
