package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.util.ArrayList;

import control.*;

/**
 * GUI used to place the initial cells.
 */
public class GameGUI extends JFrame {

    // Attributes

    private static final long serialVersionUID = 4078728479243413794L;
    private JLabel JLabelCellsLeft;
    private int width;
    private int height;
    private static PlayerManager playerManager = PlayerManager.getInstance();
    private ArrayList<GridGUI> gridList = new ArrayList<GridGUI>();
    private JPanel gridPanel;

    // Methods

    /**
     * Instanciates a grid GUI to select initial player cells
     * @param width width of the grid
     * @param height height of the grid
     */
    public GameGUI(int width, int height) {

        super("Placez vos premières cellules");
        this.width = width;
        this.height = height;

        getContentPane().setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());

        gridPanel = new JPanel(new BorderLayout());

        getContentPane().add(gridPanel, BorderLayout.CENTER);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        for (int gridIndex = 0; gridIndex < playerManager.getPlayerCount(); gridIndex++) {

            GridGUI grid = new GridGUI(this.width, this.height, gridIndex);
            grid.setCellColor(gridIndex, 0, grid.getPlayerColor());
            grid.setBounds(0, 0, 500, 500);
            gridList.add(grid);
        }

        // Determines the first player to place cells

        // Fenêtre
        setSize(width * 50, height * 50);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        update(getGraphics());
    }


}