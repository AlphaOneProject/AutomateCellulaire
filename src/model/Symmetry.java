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
        int real_x = x;
        if (x > grid.getWidth() - 1) {
            real_x = ((-this.delta-1-(((x % grid.getWidth()) + grid.getWidth()) % grid.getWidth()) % grid.getWidth()) + grid.getWidth()) % grid.getWidth();
        }
        else if (x < 0) {
            real_x = ((this.delta-1-(((x % grid.getWidth()) + grid.getWidth()) % grid.getWidth()) % grid.getWidth()) + grid.getWidth()) % grid.getWidth();
        }
        int real_y = y;
        if (y > grid.getHeight() - 1) {
            real_y = ((-this.delta-1-(((y % grid.getHeight()) + grid.getHeight()) % grid.getHeight()) % grid.getHeight()) + grid.getHeight()) % grid.getHeight();
        }
        else if (y < 0) {
            real_y = ((this.delta-1-(((y % grid.getHeight()) + grid.getHeight()) % grid.getHeight()) % grid.getHeight()) + grid.getHeight()) % grid.getHeight();
        }
        return grid.getAutomata()[real_y][real_x];
    }
}
