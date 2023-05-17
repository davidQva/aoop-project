package framework;

import javax.swing.ImageIcon;

/**
 * Tile is an interface for the tiles in the game. The tile is used to represent
 * the different tiles in the game. The tile is using the prototype pattern to
 * provide the different tiles in the game.
 */
public interface Tile extends Cloneable {

    public Object clone();

    public void setTile(ImageIcon tile);

    public ImageIcon getTile();

    public void setSize(int size);  

}
