package main.java.dev.codenmore.juegorpg.utils;

/**
 * Contador sencillo.
 */
public class Timer {

    long startTime = 0;
    long durationMillis;

    /**
     * Crea un contador.
     * @param "durationMillis" duraci�n en milisegundos.
     */
    public Timer(long durationMillis) {
        this.durationMillis = durationMillis;
    }

    /**
     * Empieza el contador asignando el comienzo con "now()"
     */
    public void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * @return true si ya pas� el tiempo de duracion establecido.
     */
    public boolean isDone() {
        return System.currentTimeMillis() - startTime >= durationMillis;
    }
}
