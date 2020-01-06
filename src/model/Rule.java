package model;

public interface Rule {
    public String getName();
    public void setNeighborhood(Neighborhood new_neighborhood);
    public State applyRule(Grid grid, Automaton automaton);
}
