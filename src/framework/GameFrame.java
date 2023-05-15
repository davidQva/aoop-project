package framework;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame {      

    public GameFrame(GameView board,AbstractTileModel game ) {
        
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
