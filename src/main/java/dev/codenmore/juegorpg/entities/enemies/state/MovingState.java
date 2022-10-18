package main.java.dev.codenmore.juegorpg.entities.enemies.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.enemies.Enemy;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.utils.Direction;
import main.java.dev.codenmore.juegorpg.utils.Utils;
import main.java.dev.codenmore.juegorpg.weapons.Weapon;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class MovingState extends EnemyState {

    public MovingState(MovingState other) {
        super(other);
    }

    public MovingState(
        Enemy enemy,
        DirectionalAnimation animation,
        Context context
    ) {
        super(enemy, animation, context);
    }

    @Override
    public void update() {
        super.update();
        Vector2D playerPos = context.getGameState().getPlayer().getPos();
        Weapon weapon = this.enemy.getWeapon();

        double distanceToPlayer = playerPos.distance(enemy.getPos());
        if (distanceToPlayer > weapon.getRange()) {
            // Si el enemigo no está dentro del rango, el ataque se mueve hacia el jugador.
            Vector2D directionVec = playerPos.subtract(enemy.getPos()).normalize();
            Direction facingDirection = Utils.getStraightDirection(directionVec);
            enemy.move(facingDirection);
        } else {
            // Jugador de ataque en el rango.
            enemy.setState(EnemyStates.ATTACKING);
        }
    }
}
