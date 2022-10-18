package main.java.dev.codenmore.juegorpg.gfx;

import main.java.dev.codenmore.juegorpg.gfx.animation.Animation;
import main.java.dev.codenmore.juegorpg.utils.Direction;

public class WorldMoveAnimation {

    private final Animation up;
    private final Animation down;
    private final Animation left;
    private final Animation right;

    public WorldMoveAnimation(Animation up, Animation down, Animation left, Animation right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void tick(Direction direction) {
        switch (direction) {
            case UP -> this.up.update();
            case DOWN -> this.down.update();
            case LEFT -> this.left.update();
            case RIGHT -> this.right.update();
        }
    }

    public void getCurrentFrame(Direction direction) {
        switch (direction) {
            case UP -> this.up.getCurrentFrame();
            case DOWN -> this.down.getCurrentFrame();
            case LEFT -> this.left.getCurrentFrame();
            case RIGHT -> this.right.getCurrentFrame();
        }
    }
}
