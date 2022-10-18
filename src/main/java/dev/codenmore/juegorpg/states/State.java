package main.java.dev.codenmore.juegorpg.states;

import java.awt.Graphics;


/**
 * Interfaz b�sica y sencilla para implementar un patron de estado gen�rico para un tipo T.
 */
public interface State<T> {
    State<T> update(T receiver);
    void render(Graphics g);
}
