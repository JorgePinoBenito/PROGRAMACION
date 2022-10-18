package main.java.dev.codenmore.juegorpg.weapons;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.gfx.GameCamera;
import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.util.Optional;

public class DebugRangedWeapon extends RangedWeapon {
    public DebugRangedWeapon(
        Context context, String name,
        long attackRate,
        int damagePerRound,
        double range,
        Entity user,
        Animator weaponFrame
    ) {
        super(context, name, attackRate, damagePerRound, range, user, weaponFrame);
    }

    public DebugRangedWeapon(Weapon other) {
        super(other);
    }

    @Override
    public Weapon copy() {
        return new DebugRangedWeapon(this);
    }

    @Override
    protected void onShoot() {

    }

    @Override
    public void render(Graphics g) {
        if (!isAttacking) return;
        GameCamera camera = context.getGameCamera();
        Rectangle bounds = new Rectangle(
            (int) camera.getX(), (int) camera.getY(), context.getWidth(), context.getHeight());
        Optional<Vector2D> intersection = ray.intersectionWith(bounds);
        if (intersection.isEmpty()) {
            Exception e = new Exception("No intersection with bounding box");
            e.printStackTrace();
            System.exit(1);
        } else {
            Vector2D start = ray.origin.subtract(camera.getVector());
            Vector2D end = intersection.get().subtract(camera.getVector());
            g.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
        }
    }
}
