package main.java.dev.codenmore.juegorpg.entities.enemies;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.MovingEntity;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.AttackingState;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.EnemyState;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.EnemyStates;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.MovingState;
import main.java.dev.codenmore.juegorpg.weapons.Weapon;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Cada enemigo debe pertenecer a este tipo. En el futuro podría convertirse en una clase abstracta para dar espacio a
 * diferentes especializaciones de enemigos.
 */
public class Enemy extends MovingEntity {
    public static final int DEFAULT_WIDTH = 25;
    public static final int DEFAULT_HEIGHT = 25;
    private  Weapon weapon;
    private MovingState movingState;
    private AttackingState attackingState;
    private EnemyState state;

    public Enemy(
        Context context, float x, float y, float moveSpeed,
        Weapon weapon,
        MovingState movingState,
        AttackingState attackingState
    ) {
        super(context, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, moveSpeed);
        this.weapon = weapon;
        this.weapon.setUser(this);
        this.movingState = movingState;
        this.movingState.setEnemy(this);
        this.attackingState = attackingState;
        this.attackingState.setEnemy(this);
        this.state = this.movingState;
    }

    public Enemy(Enemy other) {
        super(other);
        this.movingState = new MovingState(other.movingState);
        this.movingState.setEnemy(this);
        this.attackingState = new AttackingState(other.attackingState);
        this.attackingState.setEnemy(this);
        this.state = this.movingState;
        this.weapon = other.weapon.copy();
        this.weapon.setUser(this);
    }

    public Enemy copy() {
        return new Enemy(this);
    }

    @Override
    protected BufferedImage getCurrentFrame() {
        return state.getCurrentFrame();
    }

    @Override
    public void kill() {
        setActive(false);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setState(EnemyStates state) {
        switch (state) {
            case ATTACKING -> {
                this.state = attackingState;
            }
            case MOVING -> {
                this.state = movingState;
            }
        }
    }

    public Vector2D getAttackSource() {
        return getCenter();
    }

    @Override
    public void update() {
        this.state.update();
        this.weapon.update();
        super.update();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.weapon.render(g);
    }
}
