package main.java.dev.codenmore.juegorpg.ui;

import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;

public class ImageBox implements UIObject {
    private Animator animation;
    private final Bounds bounds;

    public ImageBox(Animator animation, Bounds bounds) {
        this.animation = animation;
        this.bounds = bounds;
    }


    public void setImage(Animator animation) {
        this.animation = animation;
    }

    @Override
    public void update() {
        if (animation == null) return;
        animation.update();
    }

    @Override
    public void render(Graphics g) {
        if (animation == null) return;
        g.drawImage(animation.getCurrentFrame(), (int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight(), null);
    }

    @Override
    public boolean isWithinBounds(Point point) {
        return bounds.isWithinBounds(point);
    }

    @Override
    public void onClick(MouseListenerImpl.MouseButton mouseButton) {
        bounds.onClick(mouseButton);
    }

    @Override
    public void setHovering(boolean isHovering) {
        bounds.setHovering(isHovering);
    }

    @Override
    public void move(Vector2D v) {
        bounds.move(v);
    }

    @Override
    public float getX() {
        return bounds.getX();
    }

    @Override
    public float getY() {
        return bounds.getY();
    }

    @Override
    public void setX(float x) {
        bounds.setX(x);
    }

    @Override
    public void setY(float y) {
        bounds.setY(y);
    }

    @Override
    public void setWidth(float x) {
        bounds.setWidth(x);
    }

    @Override
    public void setHeight(float y) {
        bounds.setHeight(y);
    }

    @Override
    public float getWidth() {
        return bounds.getWidth();
    }

    @Override
    public float getHeight() {
        return bounds.getHeight();
    }

    @Override
    public void centerHorOn(Bounds bounds) {
        bounds.centerHorOn(bounds);
    }

    @Override
    public void registerOn(UIManager uiManager) {
        uiManager.addObject(this);
    }
}
