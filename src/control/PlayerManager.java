package control;

import java.util.ArrayList;
import model.Player;
import model.State;

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
        else throw new IllegalArgumentException("Remove Player: Player number " + id + " does not exists.");
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
        else throw new IllegalArgumentException("Get Player: Player number " + id + " does not exists.");
    }
    
    public State[][] getPlayerGrid(int id) {
        return this.get(id).getGrid().getStates();
    }
    
    public int getPlayerCount() {
        return this.players.size();
    }
    
    public int[] getPlayerIds() {
        int[] ids = new int[this.players.size()];
        for (int i = 0; i < this.players.size(); i++) {
            ids[i] = this.players.get(i).getId();
        }
        return ids;
    }
    
    public String getPlayerName(int id) {
        return this.get(id).getName();
    }
    
    public String getPlayerRule(int id) {
        return this.get(id).getRule().getName();
    }
    
    public void setPlayerRule(int id, String rule_name) {
        this.get(id).setRule(RuleFactory.getInstance().getRule(rule_name));
    }
    
    public void next_turn() {
        for (Player player : this.players) {
            player.nextTurn();
        }
        ArrayList<Player> competitors = new ArrayList<Player>();
        Player winner;
        for (int i = 0; i < Game.getInstance().getHeight(); i++) {
            for (int j = 0; j < Game.getInstance().getWidth(); j++) {
                competitors = new ArrayList<Player>();
                for (Player player : this.players) {
                    if (player.getGrid().getStates()[i][j] != State.DEAD) competitors.add(player);
                }
                winner = competitors.get(Game.getInstance().getRandom().nextInt(competitors.size()));
                competitors.remove(winner);
                for (Player loser : competitors) {
                    loser.getGrid().getAutomata()[i][j].setState(State.DEAD);
                }
            }
        }
    }
    
    protected void updateGrid() {
        for (Player player : this.players) {
            player.setGridSize(Game.getInstance().getWidth(), Game.getInstance().getHeight());
        }
    }
}
