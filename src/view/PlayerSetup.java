package view;

import control.Game;
import control.PlayerManager;
import control.RuleManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Component;

import java.util.ArrayList;
import java.util.Random;

public class PlayerSetup extends JFrame {

    private static final long serialVersionUID = -7311347608273233951L;
    private static PlayerManager playerManager = PlayerManager.getInstance();
    private Game gameInstance = Game.getInstance();
    private ArrayList<Integer> alreadyPickedPlayers = new ArrayList<Integer>();
    private JPanel jpPlayersSelection;

    public PlayerSetup() {
        super("Initialisation");

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        this.jpPlayersSelection = new JPanel();
        jpPlayersSelection.setLayout(new BoxLayout(jpPlayersSelection, BoxLayout.Y_AXIS));
                
        setNextPlayerForm();



        content.add(jpPlayersSelection);
        getContentPane().add(content, BorderLayout.CENTER);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void setNextPlayerForm() {
        // Random choice of player
        Random randomNumber = Game.getInstance().getRandom();
        boolean valid = false;
        while (!valid)
        {
            int player = randomNumber.nextInt(playerManager.getPlayerCount());
            if (!alreadyPickedPlayers.contains(player)) 
            {
                getContentPane().removeAll();
                System.out.println("valid");
                alreadyPickedPlayers.add(player);
                valid = true;

                JPanel jpPlayerSelection = new JPanel();

                jpPlayerSelection.add(new JLabel("Nom du joueur :"));
                JTextField jtfPlayer = new JTextField();
                jtfPlayer.setColumns(10);
                jpPlayerSelection.add(jtfPlayer);
                jpPlayerSelection.add(new JLabel("Choisissez votre automate"));
                JComboBox<String> jcbRule = new JComboBox<String>(RuleManager.getInstance().getRules());
                jpPlayerSelection.add(jcbRule);
                jpPlayersSelection.add(jpPlayerSelection);
                JButton btnPlayerValid = new JButton("Valider joueur "+player);
                btnPlayerValid.addActionListener( e -> {
                    playerManager.setPlayerRule(player, jcbRule.getSelectedItem().toString());
                    System.out.println(jtfPlayer.getText());
                    System.out.println(jcbRule.getSelectedItem().toString());
                    if (alreadyPickedPlayers.size() == playerManager.getPlayerCount()){
                        setVisible(false);
                        GameGUI g = new GameGUI(gameInstance.getWidth(), gameInstance.getHeight());
                    }
                    else {
                        setNextPlayerForm();
                    }
                });
                jpPlayerSelection.add(btnPlayerValid);
                getContentPane().add(jpPlayersSelection);
                this.doLayout();
                update(getGraphics());
            }
        }
    }
}