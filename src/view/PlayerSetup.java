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

import java.awt.FlowLayout;
import java.awt.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Frame used to setup all the players one by one, randomly.
 */
public class PlayerSetup extends JFrame {

    private static final long serialVersionUID = -7311347608273233951L;
    private static PlayerManager playerManager = PlayerManager.getInstance();
    private Game gameInstance = Game.getInstance();
    
    private ArrayList<Integer> alreadyPickedPlayers = new ArrayList<Integer>();
    private JPanel jpPlayerSelection;
    private JTextField jtfPlayer;
    private JButton btnPlayerValid;
    private JComboBox<String> jcbRule;

    /**
     * Constructor
     * Creates a panel to setup a player, and a button to setup the next or launch GridGUI.
     */
    public PlayerSetup() {
        super("Initialisation");

        JPanel content = new JPanel(new FlowLayout());

        this.jpPlayerSelection = new JPanel();
        jpPlayerSelection.setLayout(new BoxLayout(jpPlayerSelection, BoxLayout.Y_AXIS));

        int player = getRandomPlayer();
        this.jpPlayerSelection.add(new JLabel("Nom du joueur :"));
        jtfPlayer = new JTextField();
        jtfPlayer.setColumns(10);
        jpPlayerSelection.add(jtfPlayer);
        jpPlayerSelection.add(new JLabel("Choisissez votre automate"));

        String[] rulesList = RuleManager.getInstance().getRules();
        ArrayList<String> rulesArrayList = new ArrayList<String>();
        Collections.addAll(rulesArrayList, rulesList);
        jcbRule = new JComboBox<String>(rulesArrayList.toArray(rulesList));
        jpPlayerSelection.add(jcbRule);
        
        this.btnPlayerValid = new JButton("Valider joueur "+player);
        btnPlayerValid.addActionListener( e -> {
            if (alreadyPickedPlayers.size() == playerManager.getPlayerCount()){
                setVisible(false);
                GameGUI g = new GameGUI(gameInstance.getWidth(), gameInstance.getHeight());
            }  
            else {
                // validating player
                int playerIdFromButton = Integer.parseInt(btnPlayerValid.getText().substring(btnPlayerValid.getText().length() - 1));
                playerManager.setPlayerName(playerIdFromButton, jtfPlayer.getText());
                playerManager.setPlayerRule(playerIdFromButton, jcbRule.getSelectedItem().toString());

                // updating gui
                int newPlayer = getRandomPlayer();
                btnPlayerValid.setText("Valider joueur "+newPlayer);
                jtfPlayer.setText("");

                if (jcbRule.getSelectedIndex() > -1) {
                    rulesArrayList.remove(jcbRule.getSelectedIndex());
                    jcbRule.removeItemAt(jcbRule.getSelectedIndex());
                    jcbRule.invalidate();
                }

                btnPlayerValid.setText("Valider joueur "+newPlayer);
                this.doLayout();
                update(getGraphics());
            }
        });
        jpPlayerSelection.add(btnPlayerValid);

        content.add(jpPlayerSelection);
        getContentPane().add(content);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Returns a random player that isn't already setup.
     * @return random player id
     */
    public int getRandomPlayer() {
        int res = -1;
        boolean valid = false;
        Random randomNumber = Game.getInstance().getRandom();
        if (alreadyPickedPlayers.size() == playerManager.getPlayerCount()) {
            GameGUI g = new GameGUI(gameInstance.getWidth(), gameInstance.getHeight());
            setVisible(false);
        }
        else {
            while (!valid)
            {
                int player = randomNumber.nextInt(playerManager.getPlayerCount());
                if (!this.alreadyPickedPlayers.contains(player)) 
                {
                    this.alreadyPickedPlayers.add(player);
                    valid = true;
                    res = player;
                }
            }
        }
        return res;
    }
}