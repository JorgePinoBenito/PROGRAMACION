package main.java.dev.codenmore.juegorpg.entities.enemies.state;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.enemies.Enemy;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.utils.Direction;

import java.awt.image.BufferedImage;

/**
 * Patr�n de estado. Controla la m�quina finita de estados que cambia el comportamiento del personaje de forma
 * polim�rfica. Es necesario heredar esta clase para implementar un nuevo tipo de estado que puede tener un enemigo.
 * Por ejemplo: para a�adir un estado de envenenado, se crea un nuevo "enemystate" de tipo "PoisonedState" que puede
 * cambiar la animacion a color verde y , por ejemplo, prohibir los ataques debido al estado �debilitado�.
 */
public abstract class EnemyState {
    protected Enemy enemy;
    protected DirectionalAnimation animation;
    protected Context context;

    public EnemyState(Enemy enemy, DirectionalAnimation animation, Context context) {
        this.enemy = enemy;
        this.animation = animation;
        this.context = context;
    }

    public EnemyState(EnemyState other) {
        this.enemy = other.enemy;
        this.animation = new DirectionalAnimation(other.animation);
        this.context = other.context;
    }

    public BufferedImage getCurrentFrame() {
        return animation.getCurrentFrame();
    }

    public void setDirection(Direction direction) {
        animation.setDirection(direction);
    }

    public void update() {
        animation.update();
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
