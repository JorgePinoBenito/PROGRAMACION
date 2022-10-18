package main.java.dev.codenmore.juegorpg.ui;

import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;

public class FillBar implements UIObject {
    private Bounds bounds;

    private int maxUnits;
    private int currentUnits;
    private Color borderColor;
    private Color fillColor;


    public FillBar(Bounds bounds, int maxUnits, int currentUnits, Color borderColor, Color fillColor) {
        this.maxUnits = maxUnits;
        this.currentUnits = currentUnits;
        this.bounds = bounds;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        Color old = new Color(g.getColor().getRGB());
        g.setColor(fillColor);
        g.fillRect(
            (int) bounds.getX(),
            (int) bounds.getY(),
            (int) bounds.getWidth() * currentUnits / maxUnits,
            (int) bounds.getHeight()
        );
        g.setColor(borderColor);
        g.drawRect(
            (int) bounds.getX(),
            (int) bounds.getY(),
            (int) bounds.getWidth(),
            (int) bounds.getHeight()
        );
        g.setColor(old);
    }

    @Override
    public boolean isWithinBounds(Point point) {
        return false;
    }

    @Override
    public void onClick(MouseListenerImpl.MouseButton mouseButton) {
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

    }

    @Override
    public void registerOn(UIManager uiManager) {
        uiManager.addObject(this);
    }

    public void setMaxUnits(int maxUnits) {
        this.maxUnits = maxUnits;
    }

    public void setCurrentUnits(int currentUnits) {
        this.currentUnits = currentUnits;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
