package main.java.dev.codenmore.juegorpg.waves;

import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.worlds.IWorld;

/**
 * Representa una oleada de enemigos, un array de oleadas en un WaveManager controla los temporizadores y los spawns (apariciones) de los
 * enemigos.
 */
public interface Wave {
    void spawn(IWorld world, EntityManager entityManager);
    boolean shouldAdvance();
    void reset();
    long getTimer();
}

