package main.java.dev.codenmore.juegorpg.weapons;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;
import main.java.dev.codenmore.juegorpg.utils.Direction;
import main.java.dev.codenmore.juegorpg.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public class MeleeWeapon extends Weapon {
    protected Rectangle hitboxFacingDown;

    public MeleeWeapon(
        Context context, String name,
        long attackRate,
        int damagePerRound,
        double range,
        Rectangle hitboxFacingDown,
        Entity user,
        Animator weaponFrame
    ) {
        super(context, name, attackRate, damagePerRound, range, user, weaponFrame);
        this.hitboxFacingDown = hitboxFacingDown;
    }

    public MeleeWeapon(MeleeWeapon other) {
        super(other);
        this.hitboxFacingDown = other.hitboxFacingDown;
    }

    @Override
    public Weapon copy() {
        return new MeleeWeapon(this);
    }

    /**
     * Establece el comportamiento al atacar con el arma. Para un arma melee, el comportamiento básico es poner un
     * hitbox en la direccion de un punto cardinal para determinar a los enemigos que afectará.
     */
    @Override
    protected void onShoot() {
        Direction direction = Utils.getStraightDirection(ray.direction);
        Rectangle hitbox = new Rectangle(0, 0, hitboxFacingDown.width, hitboxFacingDown.height);
        switch (direction) {
            case UP -> {
                hitbox.setLocation((int) user.getX() + (int) ((hitbox.getWidth() - user.getWidth()) / 2),
                    (int) user.getY() - hitbox.height);
            }
            case DOWN -> {
                hitbox.setLocation(
                    (int) user.getX() + (int) ((hitbox.getWidth() - user.getWidth()) / 2),
                    (int) user.getY() + user.getHeight()
                );
            }
            case LEFT -> {
                hitbox.setSize(hitbox.height, hitbox.width);
                hitbox.setLocation(
                    (int) user.getX() - hitbox.width,
                    (int) user.getY() + (int) ((hitbox.getHeight() - user.getHeight()) / 2)
                );
            }
            case RIGHT -> {
                hitbox.setSize(hitbox.height, hitbox.width);
                hitbox.setLocation(
                    (int) user.getX() + user.getWidth(),
                    (int) user.getY() + (int) ((hitbox.getHeight() - user.getHeight()) / 2)
                );
            }
        }
        EntityManager entityManager = context.getGameState().getEntityManager();
        ArrayList<Entity> collided = entityManager.getCollidingWith(user, hitbox);
        collided.forEach(e -> e.hurt(damagePerRound));
    }

    @Override
    public void render(Graphics g) {

    }
}
