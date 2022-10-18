package main.java.dev.codenmore.juegorpg.entities;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.utils.Direction;
import main.java.dev.codenmore.juegorpg.worlds.IWorld;

public abstract class MovingEntity extends Entity {
    protected float moveSpeed;
    protected Direction direction = Direction.DOWN;
    protected float moveX;
    protected float moveY;
    protected boolean isMoving = false;

    public MovingEntity(MovingEntity other) {
        super(other);
        this.moveSpeed = other.moveSpeed;
        this.direction = other.direction;
        this.moveX = other.moveX;
        this.moveY = other.moveY;
        this.isMoving = other.isMoving;
    }

    public MovingEntity(
        Context context,
        float x,
        float y,
        int width,
        int height,
        float moveSpeed
    ) {
        super(context, x, y, width, height);
        this.moveSpeed = moveSpeed;
    }

    public MovingEntity(
        Context context,
        float x,
        float y,
        int width,
        int height,
        int spriteWidth,
        int spriteHeight,
        float moveSpeed
    ) {
        super(context, x, y, width, height);
        this.moveSpeed = moveSpeed;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    @Override
    public void update() {
        updatePosition();
    }

    public void move(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case UP -> {
                moveY = -1 * moveSpeed;
            }
            case DOWN -> {
                moveY = moveSpeed;
            }
            case LEFT -> {
                moveX = -1 * moveSpeed;
            }
            case RIGHT -> {
                moveX = moveSpeed;
            }
        }
    }

    protected void updatePosition() {
        EntityManager entities = context.getGameState().getEntityManager();
        IWorld world = context.getGameState().getWorld();

        if (world.collidesWith(getCollisionBounds(moveX, 0)) ||             // colision con el mundo
            entities.collidesWith(this, getCollisionBounds(moveX, 0)) || // colision con otras entidades
            x + moveX <= 0 ||                                                       // colision con un borde del mapa
            x + moveX + width >= context.getGameState().getWorld().getWidth()       // colision con el otro borde
        ) {
            moveX = 0;
        }

        if (world.collidesWith(getCollisionBounds(0, moveY))
            || entities.collidesWith(this, getCollisionBounds(0, moveY)) ||
            y + moveY <= 0 ||
            y + moveY + height >= context.getGameState().getWorld().getHeight()
        ) {
            moveY = 0;
        }

        // avance en posicion
        x += moveX;
        y += moveY;

        isMoving = moveX + moveY > 0; // se esta moviendo si moveX o moveY > 0
        moveX = moveY = 0; // reset
    }
}
