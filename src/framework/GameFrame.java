package framework;

import javax.swing.JFrame;


public class GameFrame extends JFrame {

    // Board board;

    public void SetBoard(GameView board) {
        this.add(board);
        this.setSize(getPreferredSize());
        this.setVisible(true);
    }

    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        // this.add(board);
        this.setVisible(true);

    }

}
