package control;

import java.util.ArrayList;
import model.Player;

public class PlayerManager {
    
    // Attributes.
    
    private static PlayerManager instance;
    private ArrayList<Player> players;
    
    // Methods.
    
    private PlayerManager() {
        this.players = new ArrayList<Player>();
    }
    
    public static PlayerManager getInstance() {
        if (instance == null) instance = new PlayerManager();
        return instance;
    }
    
    public int add(String name) {
        int new_id = Player.getNextId();
        /*
         * WIP
        this.players.add(new Player(name));
        */
        return new_id;
    }
    
    public void remove(int id) {
        boolean found = false;
        int cursor = 0;
        while (!found && cursor < players.size()) {
            if (players.get(cursor).getId() == id) {
                found = true;
            }
            else cursor++;
        }
        if (found) players.remove(cursor);
        else throw new IllegalArgumentException("Remove Player: Player n°" + id + " does not exists.");
    }
    
    public Player get(int id) {
        boolean found = false;
        int cursor = 0;
        while (!found && cursor < players.size()) {
            if (players.get(cursor).getId() == id) {
                found = true;
            }
            else cursor++;
        }
        if (found) return players.get(cursor);
        else throw new IllegalArgumentException("Get Player: Player n°" + id + " does not exists.");
    }
}
