package model;

public class Periodicity implements Extension {
    
    public Periodicity() {}
    
    @Override
    public Automaton get(Grid grid, int x, int y) {
        return grid.getAutomata()[x % grid.getHeight()][y % grid.getWidth()];
    }
}
