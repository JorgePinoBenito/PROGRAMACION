package main.java.dev.codenmore.juegorpg.entities.player.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.player.Player;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.utils.Direction;
import main.java.dev.codenmore.juegorpg.utils.Timer;

import java.awt.image.BufferedImage;

public class DyingState extends PlayerState {
    Timer timer = new Timer(3000);

    public DyingState(
        Player player,
        Context context
    ) {
        super(player, Assets.getDirectionalAnimation("dying_state"), context);
        timer.start();
    }

    @Override
    public BufferedImage getCurrentFrame() {
        return super.getCurrentFrame();
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
    }

    @Override
    public void getInput() {
    }

    @Override
    public void update() {
        super.update();
        if (timer.isDone()) {
            player.setActive(false);
        }
    }
}
