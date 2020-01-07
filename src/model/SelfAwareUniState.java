package model;

import control.Game;

public class SelfAwareUniState implements Rule {
    
    // Attributes.
    
    private int id;
    private String name;
    private State match_state;
    private State[] self;
    private State[][] results;
    private Neighborhood neighborhood;

    // Methods.
    
    public SelfAwareUniState(int id, String name, State match_state, State[] self, State[][] results, Neighborhood neighborhood) {
        this.id = id;
        this.name = name;
        this.match_state = match_state;
        this.self = self;
        this.results = results;
        this.neighborhood = neighborhood;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Neighborhood getNeighborhood() {
        return this.neighborhood;
    }
    
    public void setNeighborhood(Neighborhood new_neighborhood) {
        this.neighborhood = new_neighborhood;
    }

    @Override
    public State applyRule(Grid grid, Automaton automaton) {
        int matching_self_state = 0;
        for (int i = 0; i < this.self.length; i++) {
            if (this.self[i] == automaton.getState()) break;
        }
        int matching_cells = 0;
        for(int[] delta : neighborhood.getNeighbors()) {
            if(Game.getInstance().getExtension().get(grid, automaton.getX() + delta[0],
               automaton.getY() + delta[1]).getState() == this.match_state) { matching_cells++; }
        }
        return this.results[matching_self_state][matching_cells];
    }
}
