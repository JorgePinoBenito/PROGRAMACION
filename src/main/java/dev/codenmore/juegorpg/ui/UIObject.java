package main.java.dev.codenmore.juegorpg.ui;

import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.entities.GameObject;
import main.java.dev.codenmore.juegorpg.input.Clickable;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public interface UIObject extends GameObject, Clickable {
    void move(Vector2D v);
    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
    void setWidth(float x);
    void setHeight(float x);
    float getWidth();
    float getHeight();
    void centerHorOn(Bounds bounds);
    void registerOn(UIManager uiManager);
}
