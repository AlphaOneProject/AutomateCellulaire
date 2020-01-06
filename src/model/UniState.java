package model;

import control.Game;

public class UniState implements Rule {
    
    // Attributes.
    
    private static int next_id = 0;
    private int id;
    private String name;
    private State match_state;
    private State[] results;
    private Neighborhood neighborhood;

    // Methods.
    
    public UniState() {
        this.id = next_id;
        next_id++;
        
        this.name = "Nom du pif";
        this.match_state = State.ALIVE;
        this.results = new State[] {State.DEAD, State.ALIVE, State.ALIVE, State.DEAD, State.DEAD};
        this.neighborhood = new Neighborhood();
    }
    
    public UniState(String name, State match_state, State[] results, Neighborhood neighborhood) {
        this.id = next_id;
        next_id++;
        
        this.name = name;
        this.match_state = match_state;
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
        int matching_cells = 0;
        for(int[] delta : neighborhood.getNeighbors()) {
            if(Game.getInstance().getExtension().get(grid, automaton.getX() + delta[0],
               automaton.getY() + delta[1]).getState() == this.match_state) { matching_cells++; }
        }
        return this.results[matching_cells];
    }
}
