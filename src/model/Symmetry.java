package model;

public class Symmetry implements Extension {
    
    // Attributes.
    
    private int delta;
    
    // Methods.
    
    public Symmetry(int delta) {
        this.delta = delta;
    }
    
    @Override
    public Automaton get(Grid grid, int x, int y) {
        // WIP
        return grid.getAutomata()[Math.min(Math.max(x, 0), grid.getHeight() + 1)]
                                 [Math.min(Math.max(y, 0), grid.getWidth() + 1)];
    }
}
