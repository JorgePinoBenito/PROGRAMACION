package main.java.dev.codenmore.juegorpg.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.gfx.GameCamera;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/*
 * Clase abstracta de la que heredan directamente o indirectamente todas las
 * entidades del juego (clases, objetos estaticos etc...)
 */
public abstract class Entity {
    public static final int DEFAULT_HEALTH = 100;
    protected Context context;
    protected float x, y;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected int spriteWidth;
    protected int spriteHeight;
    protected boolean dead = false;

    public Entity(Entity other) {
        this.context = other.context;
        this.x = other.x;
        this.y = other.y;
        this.width = other.width;
        this.health = other.health;
        this.height = other.height;
        this.spriteWidth = other.spriteWidth;
        this.spriteHeight = other.spriteHeight;
        this.active = other.active;
    }

    public Entity(Context context, float x, float y, int width, int height) {
        this.context = context;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.spriteHeight = height;
        this.spriteWidth = width;
        health = DEFAULT_HEALTH;
    }

    public Entity(Context context, float x, float y, int width, int height, int spriteWidth, int spriteHeight) {
        this.context = context;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        health = DEFAULT_HEALTH;
    }

    public abstract void update();

    public void render(Graphics g) {
        GameCamera camera = context.getGameState().getGameCamera();
        Point2D.Float p = camera.getPointRelative(new Point2D.Float(x, y));
        float newY = p.y + (height - spriteHeight);
        float newX = p.x + ((width/2f) - (spriteWidth/2f));
        g.drawImage(getCurrentFrame(), (int)newX, (int)newY, spriteWidth, spriteHeight, null);
    }

    protected abstract BufferedImage getCurrentFrame();

    public abstract void kill();

    public void hurt(int amt) {
        if (dead) return;
        health -= amt;
        System.out.printf("im being hurt, maxH %d, curH %d\n", DEFAULT_HEALTH, health);
        if (health <= 0) {
            health = 0;
            dead = true;
            kill();
        }
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + xOffset), (int) (y + yOffset), width, height);
    }

    public Rectangle getBounds() {
        return getCollisionBounds(0f, 0f);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Vector2D getPos() {
        return new Vector2D(this.x, this.y);
    }

    public Vector2D getCenter() {
        return new Vector2D(x+width/2.0, y+height/2.0);
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

