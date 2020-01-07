package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;

import control.Game;
import control.PlayerManager;

import java.awt.BorderLayout;

import java.util.Dictionary;

import java.util.Hashtable;

import javax.swing.JLabel;

import model.State;

/**
 * Grid used for placing initial cells and playing the game.
 */
public class GridGUI extends JPanel {

    // Attributes
    private static final long serialVersionUID = 6542103327377268914L;
    private JButton[][] units;
    private int cells_left;
    private int grid_width;
    private int grid_height;
    private Game game;
    private Dictionary<Integer, Color> player_colors;
    private PlayerManager player_manager;
    private int[] players;
    private int player_cursor;
    private JLabel jlTitle;
    private JPanel jpGrid;

    //Methods

    /**
     * Constructor
     * @param grid_height height of the grid
     * @param grid_width width of the grid
     */
    public GridGUI(int grid_height, int grid_width) {
        super();

        this.grid_height = grid_height;
        this.grid_height = grid_width;
        this.game = Game.getInstance();
        this.cells_left = game.getStartCellsNumber();
        this.player_manager = PlayerManager.getInstance();
        this.players = player_manager.getPlayerIds();
        this.player_colors = new Hashtable();
        this.jlTitle = new JLabel("salut antoine");
        this.jpGrid = new JPanel(new GridLayout(grid_height, grid_width));
        
        for (int i = 0; i < this.players.length; i++) {
            this.player_colors.put(this.players[i], genPlayerColor());
        }
        
        this.player_cursor = 0;

        units = new JButton[grid_height][grid_width];
        this.setLayout(new BorderLayout());      
        this.add(jlTitle);
        this.add(jpGrid);

        // Grid cells initialization
        for (int i = 0; i < grid_height; i++) {
            for (int j = 0; j < grid_width; j++) {
                units[i][j] = new JButton();
                units[i][j].setBackground(Color.WHITE);
                units[i][j].addActionListener( e -> unitClickHandler(e) );
                jpGrid.add(units[i][j]);
            }
        }
    }

    /**
     * Called when a unit is clicked.
     * @param e JButton ActionEvent
     */
    public void unitClickHandler(ActionEvent e) {
        JButton btn = (JButton)e.getSource();

        if (btn.getBackground().equals(Color.WHITE)) {
                if (this.cells_left > 0) {
                    btn.setBackground(this.player_colors.get(this.players[this.player_cursor]));
                    this.player_cursor++;
                    if (this.player_cursor >= this.players.length) {
                        this.player_cursor -= this.players.length;
                        this.cells_left--;
                    }
                else if (cells_left == 0) {
                    initCells();
                    startGame();
                }
            }
        }
    }

    /**
     * Updates players grids according to the inital cells placement.
     */
    public void initCells() {
        State[][] cells;
        for (int player : this.players) {
            cells = new State[this.grid_height][this.grid_width];
            for (int i = 0; i < this.grid_height; i++) {
                for (int j = 0; j < this.grid_width; j++) {
                    if (this.units[i][j].getBackground().equals(this.player_colors.get(player))) {
                        cells[i][j] = State.ALIVE;
                    }
                    else {
                        cells[i][j] = State.DEAD;
                    }
                }
            }
            player_manager.setPlayerGrid(player, cells.clone());
        }
    }   

    /**
     * Generates a random color used to visually identify a player.
     * @return random player color
     */
    public Color genPlayerColor() {
        Random rand = game.getRandom();
        int r = rand.nextInt((200 - 50) + 1) + 50;
        int g = rand.nextInt((200 - 50) + 1) + 50;
        int b = rand.nextInt((200 - 50) + 1) + 50;
        return new Color(r, g, b);
    }

    /**
     * Disable user interaction and starts the game.
     */
    private void startGame() {
        // Disable all units action listeners.
        for (int i = 0; i < this.grid_height; i++) {
            for (int j = 0; j < this.grid_width; j++) {
                for (ActionListener a : units[i][j].getActionListeners()) {
                    units[i][j].removeActionListener(a);
                }
            }
        }
        // Starting the simulation.
    }
}