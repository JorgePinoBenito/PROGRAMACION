package main.java.dev.codenmore.juegorpg.worlds;

import java.awt.*;
import java.util.ArrayList;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.entities.statics.Rock;
import main.java.dev.codenmore.juegorpg.entities.statics.Tree;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.tiles.TileType;
import main.java.dev.codenmore.juegorpg.tiles.TileTypeFactory;
import main.java.dev.codenmore.juegorpg.utils.Utils;

/**
 * @deprecated
 */
public class World implements IWorld {

    private Context context;
    private int width, height;
    private int spawnX, spawnY;
    private int[] tiles = null;
    private final String path;
    private final TileTypeFactory tileTypeFactory;

    public World(Context context, String path) {
        this.context = context;
        this.path = path;
        this.tileTypeFactory = new TileTypeFactory(4);
        tileTypeFactory.addTileType(0, new TileType(Assets.getStaticAnimation("grass"), false));
        tileTypeFactory.addTileType(1, new TileType(Assets.getStaticAnimation("dirt"), false));
        tileTypeFactory.addTileType(2, new TileType(Assets.getStaticAnimation("stone"), true));
    }

    @Override
    public void init(EntityManager entityManager) {
        entityManager.addEntity(new Tree(context, 100, 250));
        entityManager.addEntity(new Rock(context, 100, 450));
        entityManager.addEntity(new Tree(context, 100, 650));
        entityManager.addEntity(new Rock(context, 100, 850));
        loadWorld(this.path);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        int xStart = (int) Math.max(0, context.getGameCamera().getX() / TileType.TILEWIDTH);
        int xEnd = (int) Math.min(width, (context.getGameCamera().getX() + context.getWidth()) / TileType.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, context.getGameCamera().getY() / TileType.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (context.getGameCamera().getY() + context.getHeight()) / TileType.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * TileType.TILEWIDTH - context.getGameCamera().getX()),
                    (int) (y * TileType.TILEHEIGHT - context.getGameCamera().getY()));

            }
        }
    }

    @Override
    public float getPlayerSpawnX() {
        return this.spawnX;
    }

    @Override
    public float getPlayerSpawnY() {
        return this.spawnY;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public boolean collidesWith(Rectangle r) {
        for (int i = 0; i < tiles.length; ++i) {
            if (tileTypeFactory.getFlyweight(tiles[i]).isSolid() &&
                tileTypeFactory.getFlyweight(tiles[i]).getBounds(i % width, i / width).intersects(r)
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean collidesWith(Entity e) {
        for (int i = 0; i < tiles.length; ++i) {
            if (tileTypeFactory.getFlyweight(tiles[i]).isSolid() &&
                tileTypeFactory.getFlyweight(tiles[i]).getBounds(i % width, i / width).intersects(e.getBounds())
            ) {
                return true;
            }
        }
        return false;
    }

    public TileType getTile(int x, int y) {
        // Out of bounds tiles
        int index = Utils.toIndex(x, y, width);
        if (index >= tiles.length || index < 0)
            return tileTypeFactory.getFlyweight(0);

        return tileTypeFactory.getFlyweight(tiles[index]);
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width * height];
        for (int i = 0; i < tiles.length; ++i) {
            tiles[i] = Utils.parseInt(tokens[i + 4]);
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public ArrayList<Rectangle> getCollisionBoxes() {
        return null;
    }
}
