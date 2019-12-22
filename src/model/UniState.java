package model;

public class UniState implements Rule {
    
    // Attributes.
    
    private State match_state;
    private State[] results;
    private Neighborhood neighborhood;
    
    // Methods.
    
    public UniState(State match_state, State[] results, Neighborhood neighborhood) {
        this.match_state = match_state;
        this.results = results;
        this.neighborhood = neighborhood;
    }
    
    public Neighborhood getNeighborhood() {
        return this.neighborhood;
    }

    @Override
    public State applyRule(Grid grid, Automaton automaton) {
        int matching_cells = 0;
        for(int[] delta : neighborhood.getNeighbors()) {
            if(grid.get(automaton.getX() + delta[0], automaton.getY() + delta[1]).getState() == this.match_state) matching_cells++;
        }
        return this.results[matching_cells];
    }
}
