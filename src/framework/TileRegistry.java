package framework;
import java.util.HashMap;

public class TileRegistry {
    
    private HashMap<Integer, Tile> prototypes = new HashMap<Integer, Tile>();

    public TileRegistry() {
        prototypes = new HashMap<>();
    }

    public void addPrototypeTile(Integer key, Tile tile) {
        prototypes.put(key, tile);
    }

    public Tile getPrototypeTile(Integer key) {
        
        if (!prototypes.containsKey(key)) {
            return null;
        }    
        
        
        return prototypes.get(key);
    }

}
