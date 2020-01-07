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
    
    private boolean bitState(int number, int bit) {
        return (((number >> bit) & 1) == 1);
    }

    @Override
    public State applyRule(Grid grid, Automaton automaton) {
        State state = State.DEAD;
        int neighborhood_val = 0;
        Game game = Game.getInstance();
        if (game.getExtension().get(grid, automaton.getX() - 1, automaton.getY()).getState() != State.DEAD) neighborhood_val++;
        if (game.getExtension().get(grid, automaton.getX(), automaton.getY()).getState() != State.DEAD) neighborhood_val += 2;
        if (game.getExtension().get(grid, automaton.getX() + 1, automaton.getY()).getState() != State.DEAD) neighborhood_val += 4;
        
        if (bitState(this.number, neighborhood_val)) state = State.ALIVE;
        return state;
    }
}
