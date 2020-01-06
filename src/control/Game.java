package control;

import model.Extension;

import view.*;

public class Game {
    
    // Attributes.
    
    private static Game instance;
    private int turn_number;
    private int width;
    private int height;
    private Extension extension;
    private int start_cells_number;
    private int startingPlayers;
    
    // Methods.

    private Game() {
        this.turn_number = 0;
        this.width = 10;
        this.height = 10;
        this.extension = ExtensionFactory.getInstance().getExtension("pacman", null);
        this.start_cells_number = 1;
    }
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    public void setWidth(int new_width) {
        this.width = new_width;
        PlayerManager.getInstance().updateGrid();
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int new_height) {
        this.height = new_height;
        PlayerManager.getInstance().updateGrid();
    }

    public int getHeight() {
        return height;
    }
    
    public Extension getExtension() {
        return this.extension;
    }
    
    public void setExtension(String extension_type, Object argument) {
        this.extension = ExtensionFactory.getInstance().getExtension(extension_type, argument);
    }
    
    public int getTurnNumber() {
        return this.turn_number;
    }
    
    public void setTurnNumber(int new_turn_number) {
            this.turn_number = new_turn_number;
     }

    public int getStartCellsNumber() {
        return start_cells_number;
    }

    public void setStartCellsNumber(int new_cells_number) {
        this.start_cells_number = new_cells_number;
    }
    
    public void start() {
        // WIP
        // Will start the initial view & let the user customize their parameters.
        SetupGUI setup = new SetupGUI();        
    }
    
    public void setStartingPlayers(int players) {
        this.startingPlayers = players;
    }

    public int getStartingPlayers() {
        return startingPlayers;
    }
}
