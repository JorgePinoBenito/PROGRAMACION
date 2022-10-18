package main.java.dev.codenmore.juegorpg.entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private final Comparator<Entity> renderSorter = (a, b) -> Float.compare(
        a.getY() + a.getHeight(), b.getY() + b.getHeight());
    private ArrayList<Entity> entities = new ArrayList<>();

    public static boolean hasCollision(Entity a, Entity b) {
        return a.getBounds().intersects(b.getBounds());
    }

    public boolean collidesWith(Entity entity) {
        for (Entity e : entities) {
            if (hasCollision(entity, e)) return true;
        }
        return false;
    }

    public boolean collidesWith(Entity a, Rectangle bounds) {
        for (Entity e : entities) {
            if (e == a) continue;
            if (e.getBounds().intersects(bounds)) return true;
        }
        return false;
    }

    public ArrayList<Entity> getCollidingWith(Entity a) {
        return getCollidingWith(a, a.getBounds());
    }

    public ArrayList<Entity> getCollidingWith(Entity a, Rectangle bounds) {
        ArrayList<Entity> retVal = new ArrayList<>();
        for (Entity b : entities) {
            if (a == b) continue;
            if (bounds.intersects(b.getBounds())) {
                retVal.add(b);
            }
        }
        return retVal;
    }

    public boolean collidesWith(Rectangle r) {
        for (Entity e : this.entities) {
            if (e.getBounds().intersects(r))
                return true;
        }
        return false;
    }

    public void update() {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            e.update();
            if (!e.isActive())
                it.remove();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
