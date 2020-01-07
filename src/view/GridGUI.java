package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;

import control.*;
import model.State;

/**
 * Grid used in the GUI
 */

public class GridGUI extends JPanel {

    // Attributes
    private static final long serialVersionUID = 6542103327377268914L;
    private JButton[][] units;
    private int cellsLeft;
    private int gridWidth;
    private int gridHeight;
    private Game game;
    private Color playerColor;
    private PlayerManager playerManager;
    private int player;

    //Methods

    public GridGUI(int gridWidth, int gridHeight, int player) {
        super();

        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.game = Game.getInstance();
        this.cellsLeft = game.getStartCellsNumber();
        this.playerColor = genPlayerColor();
        this.playerManager = PlayerManager.getInstance();
        this.player = player;
        State[][] statesGrid = playerManager.getPlayerGrid(player);

        units = new JButton[gridWidth][gridHeight];
        this.setLayout(new GridLayout(gridWidth, gridHeight));      

        // Grid cells initialization
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                units[i][j] = new JButton();
                if (statesGrid[i][j].equals(State.ALIVE)) {
                    units[i][j].setBackground(playerColor);
                }
                else if (statesGrid[i][j].equals(State.DEAD)) {
                    units[i][j].setBackground(Color.WHITE);
                }
                units[i][j].addActionListener( e -> unitClickHandler(e) );
                this.add(units[i][j]);
            }
        }
    }

    public void unitClickHandler(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        
        if (btn.getBackground().equals(Color.WHITE)) {
            if (this.cellsLeft > 0) {
                btn.setBackground(this.playerColor);
                this.cellsLeft--;
            }  
        }
        if (cellsLeft == 0) {
            this.playerManager.setPlayerGrid(this.player, getInitCells());
            startGame();
        }
    }

    public State[][] getInitCells() {
        State[][] res = new State[this.gridWidth][this.gridHeight];
        for (int i = 0; i < this.gridWidth; i++) {
            for (int j = 0; j < this.gridHeight; j++) {
                if ( this.units[i][j].getBackground().equals(this.playerColor) ) {
                    res[i][j] = State.ALIVE;
                }
                else {
                    res[i][j] = State.DEAD;
                }
            }
        }
        return res;
    }   

    public Color genPlayerColor() {
        Random rand = game.getRandom();
        int r = rand.nextInt((200 - 50) + 1) + 50;
        int g = rand.nextInt((200 - 50) + 1) + 50;
        int b = rand.nextInt((200 - 50) + 1) + 50;
        return new Color(r, g, b);
    }

    public void startGame() {
        // Disable all units action listeners
        for (int i = 0; i < this.gridWidth; i++) {
            for (int j = 0; j < this.gridHeight; j++) {
                for (ActionListener a : units[i][j].getActionListeners()) {
                    units[i][j].removeActionListener(a);
                }
            }
        }
        // starting game in itself
    }

    public void setCellColor(int x, int y, Color color) {
        this.units[x][y].setBackground(color);
    }

    public Color getPlayerColor() {
        return playerColor;
    }
}