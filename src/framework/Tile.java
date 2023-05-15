package framework;
import javax.swing.ImageIcon;

public interface Tile extends Cloneable {

    public Object clone();

    public void setTile(ImageIcon tile);

    public ImageIcon getTile();

    public void setSize(int size);

    public Object getTile2();
   
}
