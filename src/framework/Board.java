package framework;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Board<T> extends JPanel{   

    private JLabel[][] label;   
    private GridLayout grid;
        
    public Board(int col, int row, int size) {     

       // this.col = col;
       // this.row = row;
        grid = new GridLayout(row, col);
        this.setLayout(grid);        

        label = new JLabel[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {                
                label[r][c] = new JLabel();
                label[r][c].setPreferredSize(new Dimension(size, size));              
                this.add(label[r][c]);
            }
        }

        //this.setPreferredSize(new Dimension (col * size, row * size));             
        //this.setLocation(0, 0);
        this.setVisible(true);       

    }

    public void setTile(ImageIcon tile, int col, int row) {
        label[row][col].setIcon(tile);     
    }

  /*      
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Board board = new Board(5, 5, 50) {
        };
        frame.add(board);
    
        Image image = ImageIO.read(new File("aoop-project/resources/player.png"));
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage); 
        //JButton icon = new JButton("tryck på mig");        
        board.setTile(scaledIcon, 4, 1);       
        frame.pack();            
      
        frame.setVisible(true);

    } */

}

