package model;

public class Automaton {
    
    // Attributes.

    private int x;
    private int y;
    private State state;
    private int age;
    
    // Methods.
    
    public Automaton(int x, int y, State state) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.age = 0;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public State getState() {
        return this.state;
    }

    public int getAge() {
        return this.age;
    }

    public void nextTurn(Grid grid, Player owner) {
        this.age++;
        this.state = owner.getRule().applyRule(grid, this);
    }
}
