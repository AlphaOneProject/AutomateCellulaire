package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;

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

    private static final long serialVersionUID = 1L;
    private JButton[][] units;
    private int cellsLeft;
    private JLabel JLabelCellsLeft;
    private int width;
    private int height;
    private static PlayerManager playerManager = PlayerManager.getInstance();

    // Methods

    /**
     * Instanciates a grid GUI to select initial player cells
     * @param width width of the grid
     * @param height height of the grid
     * @param cellsLeft number of initial cells allowed to be placed
     */
    public PlacingGUI(int width, int height, int startCellsNumber) {

        super("Placez vos premières cellules");

        this.cellsLeft = startCellsNumber;
        this.width = width;
        this.height = height;

        units = new JButton[width][height];
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(width, height));        


        topPanel.add(JLabelCellsLeft = new JLabel("Nombre de cellules restantes : " + this.cellsLeft));

        // Grid cells initialization
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                units[i][j] = new JButton();
                ((JButton)units[i][j]).setBackground(Color.WHITE);
                units[i][j].addActionListener( e -> unitClickHandler(e) );
                gridPanel.add(units[i][j]);
            }
        }

        JButton btnValidate = new JButton("Valider");
        btnValidate.addActionListener(e -> btnValidateClickHandler(e));

        // Panneaux
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(gridPanel, BorderLayout.CENTER);

        // Fenêtre
        setSize(width * 50, height * 50);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void unitClickHandler(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        
        if (btn.getBackground().equals(Color.BLACK)) {
            btn.setBackground(Color.WHITE);
            this.cellsLeft++;
        } else {
            if (this.cellsLeft > 0) {
                btn.setBackground(Color.BLACK);
                this.cellsLeft--;
            }  
        }
        JLabelCellsLeft.setText("Nombre de cellules restantes : " + this.cellsLeft);
        getContentPane().invalidate();
    }

    public void btnValidateClickHandler(ActionEvent e) {
        JButton btn = (JButton)e.getSource();

        // TODO : Donner le tableau getInitCells() au controleur
    }

    public boolean[][] getInitCells() {
        boolean[][] res = new boolean[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if ( ((JButton)this.units[i][j]).getBackground().equals(Color.BLACK) ) {
                    res[i][j] = true;
                }
                else {
                    res[i][j] = false;
                }
            }
        }
        return res;
    }
}