package framework;

import javax.swing.ImageIcon;

/**
 * Tile is an interface for the tiles in the game. The tile is used to represent
 * the different tiles in the game. The tile is using the prototype pattern to
 * provide the different tiles in the game.
 */
public interface Tile extends Cloneable {

    /**
     * Returns the type of the tile.
     * @return
     */
    public Object clone();

    /**
     * Sets the type of the tile.
     * @param tile
     */
    public void setTile(ImageIcon tile);

    public ImageIcon getTile();

    public void setSize(int size);  

}
