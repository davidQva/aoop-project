package application;
import javax.swing.ImageIcon;
import framework.Tile;

/**
 * SokobanTile implements the interface Tile.
 * SokobanTile is responsible for creating the tiles for the game.
 */
public class ImageTile implements Tile {

    ImageIcon tile;
    int size;

    /**
     * Constructor for the SokobanTile.
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
