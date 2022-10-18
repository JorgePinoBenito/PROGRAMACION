package main.java.dev.codenmore.juegorpg.waves;

import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.worlds.IWorld;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {
    private ArrayList<Wave> waves;
    private int waveEndIndex = 0;

    public WaveManager(Wave[] waves) {
        this.waves = new ArrayList<>(Arrays.asList(waves));
        if (waves.length > 0) waveEndIndex = 1;
    }

    public void spawn(IWorld world, EntityManager entityManager) {
        if (waveEndIndex > waves.size()) return;
        // Itera sobre las oleadas y generea cada uno hasta la oleada actual.
        for (int i = 0; i < waveEndIndex; ++i)
            waves.get(i).spawn(world, entityManager);
        // Si la última oleada está hecha avanzar a la siguiente.
        if (waveEndIndex <= waves.size() && waves.get(waveEndIndex - 1).shouldAdvance())
            ++waveEndIndex;
    }

    public void reset() {
        waves.forEach(Wave::reset);
        if (!waves.isEmpty()) {
            waveEndIndex = 1;
        } else {
            waveEndIndex = 0;
        }
    }

    public void setWaves(Wave[] waves) {
        this.waves = new ArrayList<>(Arrays.asList(waves));
    }

    /**
     * @return true si ya todas las oleadas se han invocado.
     */
    public boolean isDone() {
        return waveEndIndex > waves.size();
    }

    public void render(Graphics g) {
    }

    public long getCurrentTimer() {
        if (waveEndIndex > waves.size())
            return 0;
        else
            return waves.get(waveEndIndex - 1).getTimer();
    }

    public int getCurrentWave() {
        return waveEndIndex -1;
    }
}
