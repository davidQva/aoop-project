package framework;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame {

    Board board;
   
    public void SetBoard(Board board) {       
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
