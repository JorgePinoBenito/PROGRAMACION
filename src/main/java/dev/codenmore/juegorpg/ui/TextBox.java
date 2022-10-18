package main.java.dev.codenmore.juegorpg.ui;

import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;

public class TextBox implements UIObject {

    private Bounds bounds;
    private String string;

    public TextBox(Bounds bounds, String string) {
        this.bounds = bounds;
        this.string = string;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawString(this.string, (int)this.bounds.getX(), (int)this.bounds.getY() + (int)this.bounds.getHeight());
    }

    @Override
    public boolean isWithinBounds(Point point) {
        return this.bounds.isWithinBounds(point);
    }

    @Override
    public void onClick(MouseListenerImpl.MouseButton mouseButton) {

    }

    @Override
    public void setHovering(boolean isHovering) {
        this.bounds.setHovering(isHovering);
    }

    @Override
    public void move(Vector2D v) {
        this.bounds.move(v);
    }

    @Override
    public float getX() {
        return this.bounds.getX();
    }

    @Override
    public float getY() {
        return this.bounds.getY();
    }

    @Override
    public void setX(float x) {
        this.bounds.setX(x);
    }

    @Override
    public void setY(float y) {
        this.bounds.setY(y);
    }

    @Override
    public void setWidth(float x) {
        this.bounds.setWidth(x);
    }

    @Override
    public void setHeight(float x) {
        this.bounds.setHeight(x);
    }

    @Override
    public float getWidth() {
        return this.bounds.getWidth();
    }

    @Override
    public float getHeight() {
        return this.bounds.getHeight();
    }

    @Override
    public void centerHorOn(Bounds bounds) {
        this.bounds.centerHorOn(bounds);
    }

    @Override
    public void registerOn(UIManager uiManager) {
        uiManager.addObject(this);
    }

    public void setString(String string) {
        this.string = string;
    }
}
