package main.java.dev.codenmore.juegorpg.worlds;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.entities.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Interfaz que controla el comportamiento de lo que debe hacer un mundo para encajar dentro del juego.
 */
public interface IWorld extends GameObject {
    void init(EntityManager entityManager);

    @Override
    void update();

    @Override
    void render(Graphics g);

    float getPlayerSpawnX();

    float getPlayerSpawnY();

    void setContext(Context context);

    boolean collidesWith(Rectangle r);

    boolean collidesWith(Entity e);

    int getWidth();

    int getHeight();

    ArrayList<Rectangle> getCollisionBoxes();
}
