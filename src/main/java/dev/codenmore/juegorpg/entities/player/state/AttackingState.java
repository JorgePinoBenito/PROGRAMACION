package main.java.dev.codenmore.juegorpg.entities.player.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.player.Player;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.utils.Direction;
import main.java.dev.codenmore.juegorpg.utils.Utils;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class AttackingState extends PlayerState {
    public AttackingState(Player player, DirectionalAnimation animation, Context context) {
        super(player, animation, context);
    }

    @Override
    public void getInput() {
        if (this.mouseListener.mouseIsPressed(MouseListenerImpl.MouseButton.LEFT)) {
            Vector2D mousePosition = new Vector2D(mouseListener.getMouseX(), mouseListener.getMouseY())
                .add(context.getGameCamera().getVector())
                .subtract(player.getShootingSource());

            Direction facingDirection = Utils.getStraightDirection(mousePosition);
            this.animation.setDirection(facingDirection);

            player.attack();
        } else {
            player.stopAttacking();
            if (this.keyListener.right || keyListener.left || keyListener.up || keyListener.down) {
                player.setState(new MovingState(player, Assets.getDirectionalAnimation("player_move"), context));
            } else {
                player.setState(new IdlingState(player, Assets.getDirectionalAnimation("player_idle"), context));
            }
        }
    }

    @Override
    public void setDirection(Direction direction) {
    }
}
