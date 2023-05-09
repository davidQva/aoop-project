package application;

import javax.swing.ImageIcon;

import framework.Tile;

public class SokobanTile implements Tile {

    ImageIcon tile;
    int size;

    public SokobanTile clone() {
        SokobanTile clone = new SokobanTile();
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
