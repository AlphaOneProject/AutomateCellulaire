package model;

public interface Rule {
    public int getId();
    public String getName();
    public State applyRule(Grid grid, Automaton automaton);
}
