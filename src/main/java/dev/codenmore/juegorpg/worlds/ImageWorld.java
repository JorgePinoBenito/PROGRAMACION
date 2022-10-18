package main.java.dev.codenmore.juegorpg.worlds;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.gfx.GameCamera;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.min;

public class ImageWorld implements IWorld {
    private Rectangle[] solidBounds;
    private BufferedImage world;
    private Point2D.Float spawn;
    private int height;
    private int width;
    private Context context;

    public ImageWorld(Context context, Rectangle[] solidBounds, BufferedImage world, Point2D.Float spawn, int height, int width) {
        this.solidBounds = solidBounds;
        this.world = world;
        this.spawn = spawn;
        this.height = height;
        this.width = width;
        this.context = context;
    }

    public ImageWorld(Context context, Rectangle[] solidBounds, BufferedImage world, Point2D.Float spawn) {
        this.solidBounds = solidBounds;
        this.world = world;
        this.spawn = spawn;
        this.height = world.getHeight();
        this.width = world.getWidth();
        this.context = context;
    }

    @Override
    public void init(EntityManager entityManager) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        GameCamera camera = context.getGameState().getGameCamera();
        g.drawImage(
            world.getSubimage(
                (int) camera.getX(),
                (int) camera.getY(),
                min(context.getWidth(), width - (int) camera.getX()),
                min(context.getHeight(), height - (int) camera.getY())
            ),
            0,
            0,
            null
        );
    }

    @Override
    public float getPlayerSpawnX() {
        return spawn.x;
    }

    @Override
    public float getPlayerSpawnY() {
        return spawn.y;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public boolean collidesWith(Rectangle r) {
        for (Rectangle other : solidBounds) {
            if (r.intersects(other)) return true;
        }
        return false;
    }

    @Override
    public boolean collidesWith(Entity e) {
        for (Rectangle other : solidBounds) {
            if (e.getCollisionBounds(0,0).intersects(other)) return true;
        }
        return false;
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
        return new ArrayList<>(Arrays.asList(this.solidBounds));
    }
}
