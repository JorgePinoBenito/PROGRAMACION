package main.java.dev.codenmore.juegorpg.components;

import main.java.dev.codenmore.juegorpg.input.Clickable;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;

public class Bounds implements Clickable {
    private final boolean[] isClicked;
    private float x;
    private float y;
    private float width;
    private float height;
    private boolean isHovering;

    public Bounds(float x, float y, float width, float height, boolean[] isClicked) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isClicked = isClicked;
    }

    public static Bounds New(float x, float y, float sizeX, float sizeY) {
        return new Bounds(x, y, sizeX, sizeY, new boolean[3]);
    }

    public void move(Vector2D v) {
        this.x += (float)v.getX();
        this.y += (float)(v.getY());
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float sizeX) {
        this.width = sizeX;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float sizeY) {
        this.height = sizeY;
    }

    public boolean isHovering() {
        return this.isHovering;
    }

    @Override
    public void setHovering(boolean isHovering) {
        this.isHovering = isHovering;
    }

    public boolean isClicked(MouseListenerImpl.MouseButton mouseButton) {
        int index;
        switch (mouseButton) {
            case LEFT -> index = 0;
            case RIGHT -> index = 1;
            default -> index = 0;
        }
        return this.isClicked[index];
    }

    @Override
    public boolean isWithinBounds(Point point) {
        Rectangle rec = new Rectangle(
            (int)this.x,
            (int)this.y,
            (int)this.width,
            (int)this.height
        );
        return rec.contains(point);
    }

    @Override
    public void onClick(MouseListenerImpl.MouseButton mouseButton) {
        switch (mouseButton) {
            case LEFT -> isClicked[0] = true;
            case RIGHT -> isClicked[1] = true;
        }
    }

    public void centerHorOn(Bounds other) {
        float deltaX = other.getWidth() - width;
        float halfDeltaX = deltaX / 2f;
        this.x = other.getX() + halfDeltaX;
    }
}
