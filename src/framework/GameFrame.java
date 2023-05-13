package framework;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GameFrame extends JFrame {      

    public GameFrame(GameView board,AbstractTileModel game ) {
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(board);    
        this.setSize(getPreferredSize());       
        this.setVisible(true);

    }    

}
