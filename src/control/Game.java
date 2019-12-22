package control;

public class Game {
    
    // Attributes.
    
    private static Game instance;
    private int turn_number;
    
    // Methods.
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    private Game() {
        this.init();
    }
    
    private void init() {
        this.turn_number = 0;
    }
}
