package control;

import java.util.Random;

import model.Extension;
import view.SetupGUI;

public class Game {
    
    // Attributes.
    
    private static Game instance;
    private int turn_number;
    private int max_turns;
    private int width;
    private int height;
    private Extension extension;
    private int start_cells_number;
    private int startingPlayers;
    private Random random;
    
    // Methods.

    private Game() {
        this.turn_number = 0;
        this.max_turns = 10;
        this.width = 10;
        this.height = 10;
        this.extension = ExtensionFactory.getInstance().getExtension("pacman", null);
        this.start_cells_number = 1;
        this.random = new Random(java.lang.System.currentTimeMillis());
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

    public int getMaxTurns() {
        return this.max_turns;
    }

    public void setMaxTurns(int new_max_turns) {
        this.max_turns = new_max_turns;
    }

    public int getStartCellsNumber() {
        return start_cells_number;
    }

    public void setStartCellsNumber(int new_cells_number) {
        this.start_cells_number = new_cells_number;
    }
    
    public void start() {
        // Will start the initial view & let the user customize their parameters.
        SetupGUI setup = new SetupGUI();
    }
    
    public void setStartingPlayers(int players) {
        this.startingPlayers = players;
    }

    public int getStartingPlayers() {
        return startingPlayers;
    }
    
    public Random getRandom() {
        return this.random;
    }
    
    public void next_turn() {
        PlayerManager.getInstance().next_turn();
        this.turn_number++;
    }
}
