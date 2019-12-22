package model;

public class Constant implements Extension {
    
    // Attributes.
    
    private State constant_value;
    
    // Methods.
    
    public Constant(State state) {
        this.constant_value = state;
    }
    
    @Override
    public Automaton get(Grid grid, int x, int y) {
        if (x < 0 || y < 0 || x > grid.getWidth() - 1 || y > grid.getHeight() - 1) {
            return new Automaton(x, y, this.constant_value);
        }
        return grid.getAutomata()[x][y];
    }
}
