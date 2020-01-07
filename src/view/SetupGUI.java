package view;

import control.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import java.awt.FlowLayout;

public class SetupGUI extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1285322896840177969L;

    private int MAX_CELLS = 30;
    private int MAX_PLAYERS = 5;
    private Game gameInstance = Game.getInstance();

    public SetupGUI() {

        super("Combats d'automates cellulaires");

        JPanel content = new JPanel();
        content.setBounds(60, 60, 440, 440);

        JPanel jpGridSize = new JPanel(new FlowLayout());

        // Panel for grid width selection
        JPanel jpWidth = new JPanel(new FlowLayout());
        jpWidth.add(new JLabel("Largeur de la grille :"));
        JSpinner spinnerWidth = new JSpinner(new SpinnerNumberModel(20, 1, 1000, 1));
        jpWidth.add(spinnerWidth);
        jpGridSize.add(jpWidth);

        // Panel for grid height selection
        JPanel jpHeight = new JPanel(new FlowLayout());
        jpHeight.add(new JLabel("Hauteur de la grille :"));
        JSpinner spinnerHeight = new JSpinner(new SpinnerNumberModel(20, 1, 1000, 1));
        jpHeight.add(spinnerHeight);
        jpGridSize.add(jpHeight);

        // Panel for number of players selection
        JPanel jpNbPlayers = new JPanel(new FlowLayout());
        jpNbPlayers.add(new JLabel("Nombre de joueurs :"));
        JSpinner spinnerNbPlayers = new JSpinner(new SpinnerNumberModel(2, 1, MAX_PLAYERS, 1));
        jpNbPlayers.add(spinnerNbPlayers);

        // Panel of number of starting cells selection
        JPanel jpNbStartCells = new JPanel(new FlowLayout());
        jpNbStartCells.add(new JLabel("Nombre de cellules de départ"));
        JSpinner spinnerNbStartCells = new JSpinner(new SpinnerNumberModel(3, 1, MAX_CELLS, 1));
        jpNbStartCells.add(spinnerNbStartCells);

        // Panel for number of turns selection
        JPanel jpNbTurns = new JPanel(new FlowLayout());
        jpNbTurns.add(new JLabel("Nombre de tours :"));
        JSpinner spinnerNbTurns = new JSpinner(new SpinnerNumberModel(20, 1, 10000, 1));
        jpNbTurns.add(spinnerNbTurns);

        // Button to validate the form
        JButton btnValidate = new JButton("Valider");
        btnValidate.addActionListener( e -> {
            // Writing into game control
            gameInstance.setWidth( (int)spinnerWidth.getValue() );
            gameInstance.setHeight( (int)spinnerHeight.getValue() );
            gameInstance.setTurnNumber( (int)spinnerNbTurns.getValue() );
            gameInstance.setStartingPlayers( (int)spinnerNbPlayers.getValue() );
            gameInstance.setStartCellsNumber( (int)spinnerNbStartCells.getValue() );
            // Launching player setup form
            for (int i = 0; i < (int)spinnerNbPlayers.getValue(); i++) {
                PlayerManager.getInstance().add("Joueur "+(i+1));
            }
            PlayerSetup playerSetup = new PlayerSetup();
            setVisible(false);
        });

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(jpGridSize);
        content.add(jpNbPlayers);
        content.add(jpNbStartCells);
        content.add(jpNbTurns);
        content.add(btnValidate);
        getContentPane().add(content);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}