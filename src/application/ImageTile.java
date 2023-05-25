package application;
import javax.swing.ImageIcon;
import framework.Tile;

/**
 * ImageTile implements the interface Tile.
 * Tile is the prototype for the tiles in the game.
 */
public final class ImageTile implements Tile {

    ImageIcon tile;
    int size;

    /**
     * Constructor for the Tile.
     */   
    public ImageTile clone() {
        ImageTile clone = new ImageTile();
        clone.setTile(this.tile);
        clone.setSize(this.size);
        return clone;
    }

    /**
     * Sets the tile.
     * @param tile is ImageIcon for the tile.
     */
    @Override
    public void setTile(ImageIcon tile) {
       this.tile = tile;
    }

    /**
     * Returns the tile.
     * @return tile ImageIcon.
     */
    @Override
    public ImageIcon getTile() {
        return this.tile;
    }

    /**
     * Sets the size of the tile.
     * @param size is the size of the tile.
     */
    @Override
    public void setSize(int size) {
        this.size = size;
    } 
    
}
