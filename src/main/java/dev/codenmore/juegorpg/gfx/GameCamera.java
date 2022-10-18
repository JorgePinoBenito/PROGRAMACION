package main.java.dev.codenmore.juegorpg.gfx;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.geom.Point2D;

public class GameCamera {

    private Context context;
    private float x, y;

    public Vector2D getVector() {
        return new Vector2D(x, y);
    };

    public GameCamera(Context context, float x, float y) {
        this.context = context;
        this.x = x;
        this.y = y;
    }

    public void checkBlankSpace() {
        if (x < 0) {
            x = 0;
        } else if (x > context.getGameState().getWorld().getWidth() - context.getWidth()) {
            x = context.getGameState().getWorld().getWidth() - context.getWidth();
        }
        if (y < 0) {
            y = 0;
        } else if (y > context.getGameState().getWorld().getHeight() - context.getHeight()) {
            y = context.getGameState().getWorld().getHeight() - context.getHeight();
        }
    }

    public void centerOnEntity(Entity e) {
        x = e.getX() - context.getWidth() / 2 + e.getWidth() / 2;
        y = e.getY() - context.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Point2D.Float getPointRelative(Point2D.Float p) {
        return new Point2D.Float(p.x - x, p.y - y);
    }
}
