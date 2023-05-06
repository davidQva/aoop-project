package framework;

import java.util.HashMap;

public class TileRegistry {
    
    private HashMap<String, Tile> prototypes = new HashMap<String, Tile>();

    public TileRegistry() {
        prototypes = new HashMap<>();
    }

    public void addPrototypeTile(String key, Tile tile) {
        prototypes.put(key, tile);
    }

    public Tile getPrototypeTile(String key) {
        return prototypes.get(key);
    }

}
