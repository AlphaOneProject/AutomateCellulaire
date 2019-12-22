package control;

import java.util.ArrayList;
import model.Player;

public class PlayerManager {
    
    // Attributes.
    
    private static PlayerManager instance;
    private ArrayList<Player> players;
    
    // Methods.
    
    private PlayerManager() {
        this.init();
    }
    
    private void init() {
        this.players = new ArrayList<Player>();
    }
    
    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }
}
