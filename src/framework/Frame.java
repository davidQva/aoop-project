package framework;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * GameFrame is a JFrame that contains a JPanel with the game board.
 * It is used to create a window for the game.
 * Keeps track of the number of windows that are open.
 * When all windows are closed the program terminates.
 */
public class Frame extends JFrame {

    private static int windowCount = 0;

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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.setSize(board.getPreferredSize());
        this.pack();
        this.setVisible(true);





        incrementWindowCount();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                decrementWindowCount();
                if (getWindowCount() == 0) {
                    System.exit(0); // Terminate the program when all windows are closed
                }
            }
        });

        this.pack();

    }

    /**
     * Increments the window count.
     */
    private static synchronized void incrementWindowCount() {
        windowCount++;
    }

    /**
     * Decrements the window count.
     */
    private static synchronized void decrementWindowCount() {
        windowCount--;
    }

    /**
     * Returns the window count.
     * 
     * @return windowCount
     */
    private static synchronized int getWindowCount() {
        return windowCount;
    }

    
}
