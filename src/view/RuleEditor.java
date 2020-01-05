package view;

import control.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import java.awt.FlowLayout;

public class RuleEditor extends JFrame {

    /**
	 *
	 */
	private static final long serialVersionUID = 6424385432696474850L;
    private Game gameInstance = Game.getInstance();

    public RuleEditor() {

        super("Editeur de règle de jeu");
    }
}