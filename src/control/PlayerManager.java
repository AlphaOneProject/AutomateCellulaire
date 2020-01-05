package control;

import java.util.ArrayList;

import model.Neighborhood;
import model.Player;
import model.Rule;

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
        this.players.add(new Player(name, Game.getInstance().getWidth(), Game.getInstance().getHeight()));
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
    
    private Player get(int id) {
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
    
    public int getPlayerCount() {
        return this.players.size();
    }
    
    public void setPlayerRule(int id, Rule new_rule) {
        this.get(id).setRule(new_rule);
    }
    
    public void updateGrid() {
        for (Player player : this.players) {
            player.setGridSize(Game.getInstance().getWidth(), Game.getInstance().getHeight());
        }
    }
}
