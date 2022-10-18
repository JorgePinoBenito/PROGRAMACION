package main.java.dev.codenmore.juegorpg.tiles;

/**
 * @deprecated
 */
public class TileTypeFactory {
    private final TileType[] tileTypes;

    public TileTypeFactory(int size) {
        this.tileTypes = new TileType[size];
    }

    public TileType getFlyweight(int id) {
        if (id < 0 || id >= tileTypes.length)
            return tileTypes[0];
        return tileTypes[id];
    }

    public void addTileType(int id, TileType tileType) {
        if (id < 0 || id >= tileTypes.length)
            throw new Error("id is out of bounds");
        this.tileTypes[id] = tileType;
    }
}
