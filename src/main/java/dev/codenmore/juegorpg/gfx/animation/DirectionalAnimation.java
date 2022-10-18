package main.java.dev.codenmore.juegorpg.gfx.animation;

import main.java.dev.codenmore.juegorpg.utils.Direction;

import java.awt.image.BufferedImage;

public class DirectionalAnimation implements DirectionalAnimator {
    Animation up;
    Animation down;
    Animation left;
    Animation right;
    Direction direction;

    public DirectionalAnimation(Animation up, Animation down, Animation left, Animation right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.direction = Direction.DOWN;
    }

    /**
     * Este constructor de copias devuelve copias superficiales de las imágenes almacenadas en búfer en su interior.
     */
    public DirectionalAnimation(DirectionalAnimation other) {
        this.up = new Animation(other.up);
        this.down = new Animation(other.down);
        this.left = new Animation(other.left);
        this.right = new Animation(other.right);
        this.direction = other.direction;
    }

    @Override
    public void update() {
        getCurrentAnimation().update();
    }

    @Override
    public BufferedImage getCurrentFrame() {
        return getCurrentAnimation().getCurrentFrame();
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private Animation getCurrentAnimation() {
        return getAnimation(this.direction);
    }

    private Animation getAnimation(Direction direction) {
        return switch (direction) {
            case UP -> this.up;
            case DOWN -> this.down;
            case LEFT -> this.left;
            case RIGHT -> this.right;
        };
    }
}
