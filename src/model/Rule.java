package model;

public interface Rule {
    public State applyRule(Grid grid, Automaton automaton);
}
