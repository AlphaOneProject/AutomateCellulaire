package control;

import model.Extension;

public class Game {
    
    // Attributes.
    
    private static Game instance;
    private int turn_number;
    private Extension extension;
    
    // Methods.
    
    private Game() {
        this.turn_number = 0;
    }
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
