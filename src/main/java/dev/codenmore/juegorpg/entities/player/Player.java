package main.java.dev.codenmore.juegorpg.entities.player;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.MovingEntity;
import main.java.dev.codenmore.juegorpg.entities.player.state.DyingState;
import main.java.dev.codenmore.juegorpg.entities.player.state.IdlingState;
import main.java.dev.codenmore.juegorpg.entities.player.state.PlayerState;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.utils.Ray;
import main.java.dev.codenmore.juegorpg.weapons.Weapon;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Player extends MovingEntity {

    public static final float DEFAULT_SPEED = 2.0f;
    public static final int DEFAULT_PLAYER_WIDTH = 25;
    public static final int DEFAULT_PLAYER_HEIGHT = 25;
    protected String name;
    protected PlayerState state = new IdlingState(this, Assets.getDirectionalAnimation("player_idle"), context);
    protected Weapon weapon;

    public void changeWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Player(Context context, float x, float y, String name, Weapon startingWeapon) {
        super(context, x, y, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT, DEFAULT_SPEED);
        this.weapon = startingWeapon;
        this.weapon.setUser(this);
        this.name = name;
    }

    public Player(Context context, float x, float y, int spriteWidth, int spriteHeight, String name, Weapon startingWeapon) {
        super(context, x, y, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT, spriteWidth, spriteHeight, DEFAULT_SPEED);
        this.name = name;
        this.weapon = startingWeapon;
        this.weapon.setUser(this);
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerState getState() {
        return state;
    }

    @Override
    public void update() {
        getInput();
        super.update();
        state.setDirection(direction);
        state.update();
        weapon.update();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void kill() {
        setState(new DyingState(this, context));
        System.out.println("You lose");
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.weapon.render(g);
    }

    @Override
    protected BufferedImage getCurrentFrame() {
        return this.state.getCurrentFrame();
    }

    private void getInput() {
        state.getInput();
    }

    public void stopAttacking() {
        weapon.stopAttacking();
    }

    public void attack() {
        MouseListenerImpl mouseListener = context.getMouseListener();
        Vector2D playerOrigin = getShootingSource();
        Vector2D clickPosition = new Vector2D(mouseListener.getMouseX(), mouseListener.getMouseY())
            .add(context.getGameCamera().getVector());
        Vector2D direction = clickPosition.subtract(playerOrigin).normalize();
        weapon.attack(new Ray(getShootingSource(), direction));
    }

    public Vector2D getShootingSource() {
        return new Vector2D(x + width / 2f, y + height / 2f);
    }

    public float getSpeed() {
        return this.moveSpeed;
    }

    public void setSpeed(float speed) {
        this.moveSpeed = speed;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }
}