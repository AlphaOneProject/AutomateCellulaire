package model;

public class Pacman implements Extension {

    @Override
    public Automaton get(Grid grid, int x, int y) {
        return grid.getAutomata()[x % grid.getHeight()][y % grid.getWidth()];
    }
}
