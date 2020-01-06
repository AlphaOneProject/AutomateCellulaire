package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;

import control.*;

/**
 * GUI used to place the initial cells.
 */

/**
 * TODO : 
 * retourner la liste des cellules choisies
 * mettre en oeuvre les relations avec le modèle et le controleur
 * alterner les joueurs
 */
public class PlacingGUI extends JFrame {

    // Attributes

    private static final long serialVersionUID = 4078728479243413794L;
    private JLabel JLabelCellsLeft;
    private int width;
    private int height;
    private static PlayerManager playerManager = PlayerManager.getInstance();
    private ArrayList<GridGUI> gridList = new ArrayList<GridGUI>();
    private JLayeredPane gridsLayeredPane;

    // Methods

    /**
     * Instanciates a grid GUI to select initial player cells
     * @param width width of the grid
     * @param height height of the grid
     * @param cellsLeft number of initial cells allowed to be placed
     */
    public PlacingGUI(int width, int height) {

        super("Placez vos premières cellules");
        this.width = width;
        this.height = height;

        getContentPane().setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());

        gridsLayeredPane = new JLayeredPane();
        gridsLayeredPane.setPreferredSize(new Dimension(500, 500));

        getContentPane().add(gridsLayeredPane);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        for (int gridIndex = 0; gridIndex < playerManager.getPlayerCount(); gridIndex++) {

            GridGUI grid = new GridGUI(this.width, this.height);
            grid.setCellColor(gridIndex, 0, grid.getPlayerColor());
            grid.setBounds(0, 0, 500, 500);
            gridList.add(grid);
            
            gridsLayeredPane.add(grid, JLayeredPane.DEFAULT_LAYER);
            gridsLayeredPane.setLayer(grid, new Integer(playerManager.getPlayerCount() - gridIndex));
        }

        // Fenêtre
        setSize(width * 50, height * 50);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        update(getGraphics());
    }
}