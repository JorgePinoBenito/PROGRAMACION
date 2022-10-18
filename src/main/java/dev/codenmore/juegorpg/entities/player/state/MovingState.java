package main.java.dev.codenmore.juegorpg.entities.player.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.player.Player;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.utils.Direction;

public class MovingState extends PlayerState {
    public MovingState(Player player, DirectionalAnimation animation, Context context) {
        super(player, animation, context);
    }

    @Override
    public void getInput() {
        if (this.mouseListener.mouseIsPressed(MouseListenerImpl.MouseButton.LEFT)) {
            player.setState(new AttackingState(player, Assets.getDirectionalAnimation("player_attack"), context));
            return;
        }
        if (this.keyListener.right || keyListener.left || keyListener.up || keyListener.down) {
            if (this.keyListener.right)
                player.move(Direction.RIGHT);
            if (this.keyListener.left)
                player.move(Direction.LEFT);
            if (this.keyListener.up)
                player.move(Direction.UP);
            if (this.keyListener.down)
                player.move(Direction.DOWN);
        } else {
            player.setState(new IdlingState(player, Assets.getDirectionalAnimation("player_idle"), context));
        }
    }
}
