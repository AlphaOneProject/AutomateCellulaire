package view;

import control.Game;
import control.PlayerManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;

import java.util.ArrayList;

/**
 * TODO : mettre en oeuvre les relations avec le modèle et les contrôleurs
 */
public class PlayerSetupGUI extends JFrame {

    private static final long serialVersionUID = -7311347608273233951L;
    private static PlayerManager playerManager = PlayerManager.getInstance();
    private int MAX_PLAYERS = 2;
    private String[] RULE_LIST = {"FRIEDKIN", "GAME OF LIFE"};
    private Game gameInstance = Game.getInstance();

    public PlayerSetupGUI() {

        super("Choix de l'automate");

        JPanel content = new JPanel();
        content.setBounds(60, 60, 440, 440);

        JPanel jpGridSize = new JPanel(new FlowLayout());

        JPanel jpWidth = new JPanel(new FlowLayout());
        jpWidth.add(new JLabel("Largeur de la grille :"));
        JSpinner spinnerWidth = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        jpWidth.add(spinnerWidth);
        jpGridSize.add(jpWidth);

        JPanel jpHeight = new JPanel(new FlowLayout());
        jpHeight.add(new JLabel("Hauteur de la grille :"));
        JSpinner spinnerHeight = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        jpHeight.add(spinnerHeight);
        jpGridSize.add(jpHeight);

        JPanel jpNbPlayers = new JPanel(new FlowLayout());
        jpNbPlayers.add(new JLabel("Nombre de joueurs :"));
        JSpinner spinnerNbPlayers = new JSpinner(new SpinnerNumberModel(1, 0, MAX_PLAYERS, 1));
        jpNbPlayers.add(spinnerNbPlayers);

        // JPanel jpExtension = new JPanel(new FlowLayout());
        // jpExtension.add(new JLabel("Méthode d'extension :"));
        // jpExtension.add(new JComboBox<String>(EXT_LIST));
        // TODO : Les extensions séléctionnées par les autres joueurs ne sont plus disponibles

        JPanel jpNbStartCells = new JPanel(new FlowLayout());
        jpNbStartCells.add(new JLabel("Nombre de cellules de départ"));
        JSpinner spinnerNbStartCells = new JSpinner(new SpinnerNumberModel(1, 0, MAX_CELLS, 1));
        jpNbStartCells.add(spinnerNbStartCells);

        JPanel jpNbTurns = new JPanel(new FlowLayout());
        jpNbTurns.add(new JLabel("Nombre de tours :"));
        JSpinner spinnerNbTurns = new JSpinner(new SpinnerNumberModel(1, 0, 10000, 1));
        jpNbTurns.add(spinnerNbTurns);

        JButton btnValidate = new JButton("Valider");
        btnValidate.addActionListener( e -> {
            gameInstance.setWidth( (int)spinnerWidth.getValue() );
            gameInstance.setHeight( (int)spinnerHeight.getValue() );
            gameInstance.setTurnNumber( (int)spinnerNbTurns.getValue() );
            gameInstance.setStartCellsNumber( (int)spinnerNbStartCells.getValue() );
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