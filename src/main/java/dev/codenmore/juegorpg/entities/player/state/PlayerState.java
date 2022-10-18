package main.java.dev.codenmore.juegorpg.entities.player.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.player.Player;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.input.KeyListenerImpl;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.utils.Direction;

import java.awt.image.BufferedImage;

public abstract class PlayerState {

    protected Player player;
    protected DirectionalAnimation animation;
    protected Context context;
    protected KeyListenerImpl keyListener;
    protected MouseListenerImpl mouseListener;

    public PlayerState(Player player, DirectionalAnimation animation, Context context) {
        this.player = player;
        this.animation = animation;
        this.context = context;
        this.keyListener = context.getKeyListener();
        this.mouseListener = context.getMouseListener();
    }

    public BufferedImage getCurrentFrame() {
        return animation.getCurrentFrame();
    }

    public void setDirection(Direction direction) {
        animation.setDirection(direction);
    }

    public abstract void getInput();

    public void update() {
        animation.update();
    }
}
