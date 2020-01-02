package control;

import model.Extension;

public class Game {
    
    // Attributes.
    
    private static Game instance;
    private int turn_number;
    private int width;
    private int height;
    private Extension extension;
    
    // Methods.

    private Game() {
        this.turn_number = 0;
        this.width = 10;
        this.height = 10;
    }
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    public void setWidth(int new_width) {
        this.width = new_width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int new_height) {
        this.height = new_height;
    }

    public int getHeight() {
        return height;
    }
    
    public Extension getExtension() {
        return this.extension;
    }
    
    public void setExtension(Extension new_extension) {
        this.extension = new_extension;
    }
}
