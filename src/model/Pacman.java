package model;

public class Pacman implements Extension {
    
    public Pacman() {}
    
    @Override
    public Automaton get(Grid grid, int x, int y) {
        return grid.getAutomata()[((y % grid.getHeight()) + grid.getHeight()) % grid.getHeight()]
                                 [((x % grid.getWidth()) + grid.getWidth()) % grid.getWidth()];
    }
}
