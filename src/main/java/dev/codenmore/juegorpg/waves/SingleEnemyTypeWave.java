package main.java.dev.codenmore.juegorpg.waves;

import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.entities.enemies.Enemy;
import main.java.dev.codenmore.juegorpg.worlds.IWorld;

import java.util.Optional;
import java.util.Random;

/**
 * Representa una oleada de enemigos que solo tiene un tipo de enemigo.
 */
public class SingleEnemyTypeWave implements Wave {
    private Optional<Long> waveStartTime = Optional.empty();
    private final long duration;
    private final Enemy enemyPrototype;
    private final int maxToSpawn;
    private int leftToSpawn;
    private boolean shouldAdvance;
    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();
    private Random random = new Random(System.nanoTime());

    public SingleEnemyTypeWave(long duration, Enemy enemyPrototype, int maxToSpawn, long spawnInterval) {
        this.duration = duration;
        this.enemyPrototype = enemyPrototype;
        this.maxToSpawn = maxToSpawn;
        this.spawnInterval = spawnInterval;
        this.leftToSpawn = maxToSpawn;
    }

    @Override
    public void spawn(IWorld world, EntityManager entityManager) {
        long now = System.currentTimeMillis();

        if (waveStartTime.isEmpty())
            waveStartTime = Optional.of(now);

        if (now - waveStartTime.get() > duration) {
            shouldAdvance = true;
        }

        // Rutina de generación.
        if (leftToSpawn == 0 || now - lastSpawnTime < spawnInterval) {
            return;
        }

        // Obtener una coordenada x, y aleatoria en el mundo que sea libre de colocar al enemigo recién generado.
        do {
            // El 30 y el 60 son tolerancias para evitar spawn (aparición) en el borde.
            int xSpawn = (int) (30 + random.nextDouble() * (world.getWidth()-60));
            int ySpawn = (int) (30 + random.nextDouble() * (world.getHeight()-60));
            enemyPrototype.setX(xSpawn);
            enemyPrototype.setY(ySpawn);
        } while (world.collidesWith(enemyPrototype) || entityManager.collidesWith(enemyPrototype));

        Enemy newEnemy = enemyPrototype.copy(); // Necesitamos una copia profunda del prototipo para colocar en el juego.
        entityManager.addEntity(newEnemy);

        --leftToSpawn;
        lastSpawnTime = now;
    }

    @Override
    public boolean shouldAdvance() {
        return shouldAdvance;
    }

    @Override
    public void reset() {
        leftToSpawn = maxToSpawn;
        shouldAdvance = false;
    }

    @Override
    public long getTimer() {
        long now = System.currentTimeMillis();
        return waveStartTime
            .map(aLong -> duration - (now - aLong))
            .orElse(0L);
    }
}
