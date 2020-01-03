package view;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * GUI used to place the initial cells.
 */

/**
 * TODO : Retourner la liste des cellules choisies
 * 
 */
public class PlacingGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton[][] units;

    private int width;

    private int height;

    private int cellsLeft;

    public PlacingGUI(int width, int height) {

        super("Placez vos premières cellules");
        units = new JButton[width][height];
        getContentPane().setLayout(new GridLayout(width, height));

        setSize(width * 50, height * 50);
        setResizable(false);

        // Grid cells initialization
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                units[i][j] = new JButton();
                units[i][j].addActionListener( e -> unitClickHandler(e) );
                getContentPane().add(units[i][j]);
            }
        }

        setVisible(true);
    }

    public void unitClickHandler(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        btn.setBackground(Color.BLACK);
    }
}