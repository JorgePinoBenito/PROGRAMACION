package main.java.dev.codenmore.juegorpg.weapons;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;
import main.java.dev.codenmore.juegorpg.utils.Ray;

import java.awt.*;

/**
 * Clase base de cada arma. Para atacar es necesario tener un arma. 
 * Para crear una nueva arma, basta con instanciar alguna subclase concreta con los parámetros deseados.
 */
public abstract class Weapon {
    protected Entity user;
    protected final String name;
    protected Context context;
    protected boolean isAttacking = false;
    protected long attackRate;
    protected long lastShot = System.currentTimeMillis();
    protected Ray ray = null;
    protected int damagePerRound;
    protected Animator weaponFrame;

    protected double range; // Si el rango es 0, entonces es un rango infínito.

    public Weapon(
        Context context, String name,
        long attackRate, int damagePerRound, double range,
        Entity user,
        Animator weaponFrame
    ) {
        this.context = context;
        this.name = name;
        this.attackRate = attackRate;
        this.damagePerRound = damagePerRound;
        this.range = range;
        this.user = user;
        this.weaponFrame = weaponFrame;
    }

    public Weapon(Weapon other) {
        this.context = other.context;
        this.name = other.name;
        this.attackRate = other.attackRate;
        this.damagePerRound = other.damagePerRound;
        this.range = other.range;
        this.user = null;
        this.ray = other.ray;
        this.lastShot = other.lastShot;
        this.isAttacking = other.isAttacking;
        this.weaponFrame = other.weaponFrame;
    }

    public abstract Weapon copy();

    public void setUser(Entity user) {
        this.user = user;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void attack(Ray ray) {
        isAttacking = true;
        this.ray = ray;
    }

    public long getAttackRate() {
        return attackRate;
    }

    protected abstract void onShoot();

    public void update() {
        if (isAttacking && canShoot()) {
            lastShot = System.currentTimeMillis();
            onShoot();
        }
    }

    public abstract void render(Graphics g);

    private boolean canShoot() {
        return System.currentTimeMillis() - lastShot >= attackRate;
    }

    public void stopAttacking() {
        isAttacking = false;
    }

    public String name() {
        return name;
    }

    public double getRange() {
        return range;
    }

    public Animator getFrame() {
        return this.weaponFrame;
    }
}
