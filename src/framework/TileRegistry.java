package framework;

import java.util.HashMap;

/**
 * TileRegistry is a class that contains the prototypes of the tiles. This class
 * is used to create new tiles in the game. The class is using the prototype
 * pattern to create new tiles.
 */
public class TileRegistry {

    private HashMap<Integer, Tile> prototypes = new HashMap<Integer, Tile>();

    /**
     * Constructor for the TileRegistry class.
     */
    public TileRegistry() {
        prototypes = new HashMap<>();
    }

    /**
     * Adds a prototype tile to the registry.
     * Integer is used as key to asoociate the prototype with a number on the board.
     * 
     * @param key  is the key for the prototype.
     * @param tile is the prototype tile.
     */
    public void addPrototypeTile(Integer key, Tile tile) {
        prototypes.put(key, tile);
    }

    /**
     * Returns a prototype tile from the registry.
     * 
     * @param key is the key for the prototype.
     * @return prototype tile.
     */
    public Tile getPrototypeTile(Integer key) {

        if (!prototypes.containsKey(key)) {
            return null;
        }

        return prototypes.get(key);
    }

}
