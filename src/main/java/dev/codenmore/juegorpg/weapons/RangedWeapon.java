package main.java.dev.codenmore.juegorpg.weapons;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;
import main.java.dev.codenmore.juegorpg.worlds.IWorld;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class RangedWeapon extends Weapon {
    public RangedWeapon(
        Context context,
        String name,
        long attackRate,
        int damagePerRound,
        double range,
        Entity user,
        Animator weaponFrame
    ) {
        super(context, name, attackRate, damagePerRound, range, user, weaponFrame);
    }

    public RangedWeapon(Weapon other) {
        super(other);
    }

    @Override
    public Weapon copy() {
        return new RangedWeapon(this);
    }

    @Override
    protected void onShoot() {
        EntityManager entityManager = context.getGameState().getEntityManager();
        IWorld world = context.getGameState().getWorld();

        ArrayList<Entity> entities = entityManager.getEntities();

        boolean minSet = false;
        Entity minElem = null;
        double min = 0;
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == user) continue;
            Optional<Vector2D> intersection = ray.intersectionWith(entities.get(i).getBounds());
            if (!minSet) {
                if (intersection.isPresent()) {
                    minSet = true;
                    min = intersection.get().distance(user.getCenter());
                    minElem = entities.get(i);
                }
                continue;
            }

            if (intersection.isPresent()) {
                double cur = intersection.get().distance(user.getCenter());
                if (cur < min) {
                    min = cur;
                    minElem = entities.get(i);
                }
            }
        }

        if (!minSet) {
            return;
        }

        double minWorldDist = min;
        ArrayList<Rectangle> worldSolidBounds = world.getCollisionBoxes();
        for (Rectangle r : worldSolidBounds) {
            Optional<Vector2D> intersection = ray.intersectionWith(r);
            if (intersection.isPresent()) {
                double dist = intersection.get().distance(user.getCenter());
                if (dist < minWorldDist) {
                    minWorldDist = dist;
                }
            }
        }

        // Verificación de rango.
        if (min <= minWorldDist && min <= range) {
            minElem.hurt(damagePerRound);
        }
    }

    @Override
    public void render(Graphics g) {
    }
}
