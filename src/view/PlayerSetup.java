package view;

import control.Game;
import control.PlayerManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.Component;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO : mettre en oeuvre les relations avec le modèle et les contrôleurs
 */
public class PlayerSetup extends JFrame {

    private static final long serialVersionUID = -7311347608273233951L;
    private static PlayerManager playerManager = PlayerManager.getInstance();
    private Game gameInstance = Game.getInstance();

    public PlayerSetup() {
        super("Initialisation");

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JLabel lblTitle = new JLabel("Choix du type d'automate");
        content.add(lblTitle);

        JPanel jpPlayersSelection = new JPanel();
        jpPlayersSelection.setLayout(new BoxLayout(jpPlayersSelection, BoxLayout.Y_AXIS));

        ArrayList<Integer> alreadyPickedPlayers = new ArrayList<Integer>();
        Random randomNumber = new Random();
                
        for (int i = 0; i < gameInstance.getStartingPlayers(); i++) {

            // Random choice of player
            boolean valid = false;
            while (!valid)
            {
                
                int temp = randomNumber.nextInt(gameInstance.getStartingPlayers());
                if (!alreadyPickedPlayers.contains(temp)) 
                {
                    alreadyPickedPlayers.add(temp);
                    valid = true;

                    JPanel jpPlayerSelection = new JPanel();
                    // jpPlayerSelection.setLayout(new BoxLayout(jpPlayerSelection, BoxLayout.Y_AXIS));

                    jpPlayerSelection.add(new JLabel("Nom du joueur :"));
                    JTextField jtfPlayer = new JTextField();
                    jtfPlayer.setColumns(10);
                    jpPlayerSelection.add(jtfPlayer);
                    jpPlayerSelection.add(new JLabel("Choisissez votre automate"));
                    jpPlayersSelection.add(jpPlayerSelection);
                }
            }
        }
        JButton btnValidate = new JButton("Valider");
        btnValidate.addActionListener( e -> {

            // Get text of textfields and create players by the name chosen.
            for (Component c : jpPlayersSelection.getComponents()) {
                if (c instanceof JPanel) {
                    for (Component p : ((JPanel)c).getComponents()) {
                        if (p instanceof JTextField) {
                            System.out.println(((JTextField)p).getText());
                            playerManager.add( ((JTextField)p).getText() );
                        }
                    }
                }
            }
            setVisible(false);
            PlacingGUI p = new PlacingGUI(gameInstance.getWidth(), gameInstance.getHeight(), gameInstance.getStartCellsNumber());
        });
        jpPlayersSelection.add(btnValidate);
        content.add(jpPlayersSelection);
        getContentPane().add(content);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}