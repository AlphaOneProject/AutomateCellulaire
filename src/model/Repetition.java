package model;

public class Repetition implements Extension {
    
    public Repetition() {}
    
    @Override
    public Automaton get(Grid grid, int x, int y) {
        return grid.getAutomata()[Math.min(Math.max(x, 0), grid.getHeight() + 1)]
                                 [Math.min(Math.max(y, 0), grid.getWidth() + 1)];
    }
}
