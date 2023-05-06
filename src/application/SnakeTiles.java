package application;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import framework.Tile;

public class SnakeTiles implements Tile {

    ImageIcon tile;
    int size;

    public SnakeTiles clone() {
        SnakeTiles clone = new SnakeTiles();
        clone.setTile(this.tile);
        clone.setSize(this.size);
        return clone;
    }

    @Override
    public void setTile(ImageIcon tile) {
       this.tile = tile;
    }

    @Override
    public ImageIcon getTile() {
        return this.tile;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }   
    
}
