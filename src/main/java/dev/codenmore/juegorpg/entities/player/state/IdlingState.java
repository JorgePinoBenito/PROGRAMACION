package main.java.dev.codenmore.juegorpg.entities.player.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.player.Player;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;

public class IdlingState extends PlayerState {
    public IdlingState(Player player, DirectionalAnimation animation, Context context) {
        super(player, animation, context);
    }

    @Override
    public void getInput() {
        if (mouseListener.mouseIsPressed(MouseListenerImpl.MouseButton.LEFT)) {
            player.setState(new AttackingState(player, Assets.getDirectionalAnimation("player_attack"), context));
        } else if (keyListener.down || keyListener.up || keyListener.left || keyListener.right)  {
            player.setState(new MovingState(player, Assets.getDirectionalAnimation("player_move"), context));
        }
    }
}
