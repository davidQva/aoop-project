package application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class RedRectangle extends Component {

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(10, 10, 50, 50); // x, y, width, height
    }    
}
