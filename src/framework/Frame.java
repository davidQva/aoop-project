package framework;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GameFrame is a JFrame that contains a JPanel with the game board.
 */
public class Frame extends JFrame {

    /**
     * Constructor for GameFrame
     * 
     * @param board is the JPanel that contains the game board.
     * @param game  is the model for the game.
     */
    public Frame(View board, AbstractTileModel game) {

        this.setTitle(game.getClass().getSimpleName());
        JPanel panel = new JPanel();
        panel.setSize(getPreferredSize());
        panel.add(board);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.setSize(board.getPreferredSize());
        this.pack();
        this.setVisible(true);

    }

}
