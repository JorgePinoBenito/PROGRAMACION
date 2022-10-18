package main.java.dev.codenmore.juegorpg.entities.enemies.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.enemies.Enemy;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.utils.Direction;
import main.java.dev.codenmore.juegorpg.utils.Ray;
import main.java.dev.codenmore.juegorpg.utils.Utils;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class AttackingState extends EnemyState {

    public AttackingState(
        Enemy enemy,
        DirectionalAnimation animation,
        Context context
    ) {
        super(enemy, animation, context);
    }

    public AttackingState(EnemyState other) {
        super(other);
    }

    @Override
    public void setDirection(Direction direction) {
    }

    @Override
    public void update() {
        super.update();
        Vector2D playerPos = context.getGameState().getPlayer().getCenter();
        boolean inRange = playerPos.distance(enemy.getAttackSource()) <= enemy.getWeapon().getRange();
        if (inRange) {
            Ray ray = new Ray(enemy.getAttackSource(), playerPos.subtract(enemy.getAttackSource()));
            super.setDirection(Utils.getStraightDirection(ray.direction));
            enemy.getWeapon().attack(ray);
            enemy.getWeapon().update();
        } else {
            enemy.getWeapon().stopAttacking();
            enemy.setState(EnemyStates.MOVING);
        }
    }
}
