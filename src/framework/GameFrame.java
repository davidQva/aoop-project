package framework;
import javax.swing.JFrame;


public class GameFrame extends JFrame {      

    public GameFrame(GameView board,TileModel game ) {

        this.addKeyListener(new Controller(game, board));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(board);    
        this.setSize(getPreferredSize());
        // this.add(board);
        this.setVisible(true);

    }

}
