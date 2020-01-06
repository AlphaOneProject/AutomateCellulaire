package view;

import control.*;

import javax.swing.JFrame;

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