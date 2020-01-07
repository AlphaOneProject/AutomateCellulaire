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
import java.util.Random;

public class PlayerSetup extends JFrame {

    private static final long serialVersionUID = -7311347608273233951L;
    private static PlayerManager playerManager = PlayerManager.getInstance();
    private Game gameInstance = Game.getInstance();
    
    private ArrayList<Integer> alreadyPickedPlayers = new ArrayList<Integer>();
    private JPanel jpPlayerSelection;
    private JTextField jtfPlayer;
    private JButton btnPlayerValid;
    private JComboBox<String> jcbRule;

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
        jcbRule = new JComboBox<String>(RuleManager.getInstance().getRules());
        jpPlayerSelection.add(jcbRule);
        content.add(jpPlayerSelection);
        this.btnPlayerValid = new JButton("Valider joueur "+player);
        btnPlayerValid.addActionListener( e -> {
            if (alreadyPickedPlayers.size() == playerManager.getPlayerCount()){
                setVisible(false);
                GameGUI g = new GameGUI(gameInstance.getWidth(), gameInstance.getHeight());
            }  
            else {
                int playerIdFromButton = Integer.parseInt(btnPlayerValid.getText().substring(btnPlayerValid.getText().length() - 1));
                playerManager.setPlayerRule(playerIdFromButton, jcbRule.getSelectedItem().toString());

                jcbRule.remove(jcbRule.getSelectedIndex());
                btnPlayerValid.setText("Valider joueur "+getRandomPlayer());
                System.out.println(jtfPlayer.getText());
                System.out.println(jcbRule.getSelectedItem().toString());
                jcbRule.remove(jcbRule.getSelectedIndex());
                btnPlayerValid.setText("Valider joueur "+getRandomPlayer());
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

    public int getRandomPlayer() {
        int res = -1;
        boolean valid = false;
        Random randomNumber = Game.getInstance().getRandom();
        while (!valid)
        {
            System.out.println("pas valide");
            int player = randomNumber.nextInt(playerManager.getPlayerCount());
            if (!this.alreadyPickedPlayers.contains(player)) 
            {
                this.alreadyPickedPlayers.add(player);
                valid = true;
                res = player;
            }
        }
        return res;
    }
}