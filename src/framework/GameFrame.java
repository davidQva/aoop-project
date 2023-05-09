package framework;
import javax.swing.JFrame;


public class GameFrame extends JFrame {      

    public GameFrame(GameView board,AbstractTileModel game ) {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(board);    
        this.setSize(getPreferredSize());       
        this.setVisible(true);

    }

}
