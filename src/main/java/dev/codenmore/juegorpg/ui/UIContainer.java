package main.java.dev.codenmore.juegorpg.ui;

import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.entities.GameObject;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.util.ArrayList;


public class UIContainer implements UIObject {
    private ArrayList<UIObject> elements;
    private Bounds bounds;

    public UIContainer(ArrayList<UIObject> elements, Bounds bounds) {
        this.elements = elements;
        this.bounds = bounds;
    }

    public static UIContainer NewDefault(ArrayList<UIObject> elements) {
        Bounds bounds = Bounds.New(0,0, 0 , 0);
        UIContainer retVal = new UIContainer(elements, bounds);
        retVal.fitToContents();
        return retVal;
    }

    public ArrayList<UIObject> getObjects() {
        return this.elements;
    }

    @Override
    public void update() {
        this.elements.forEach(GameObject::update);
    }

    @Override
    public void render(Graphics g) {
        this.elements.forEach(x -> x.render(g));
    }

    @Override
    public boolean isWithinBounds(Point point) {
        return bounds.isWithinBounds(point);
    }

    @Override
    public void onClick(MouseListenerImpl.MouseButton mouseButton) {
    }

    @Override
    public void setHovering(boolean isHovering) {
        this.bounds.setHovering(isHovering);
    }

    public UIContainer alignHorizontally() {
        this.elements.forEach(x -> x.centerHorOn(this.bounds));
        return this;
    }

    public UIContainer distributeHorizontally() {
        float padding = 1;
        this.elements.sort((x1, x2) -> Float.compare(x1.getX(), x2.getX()));
        elements.get(0).setX(this.bounds.getX());
        for (int i = 1; i < elements.size(); ++i) {
            elements.get(i).setX(elements.get(i - 1).getX() + elements.get(i - 1).getWidth() + padding);
        }
        return this;
    }

    public UIContainer distributeVertically() {
        float padding = 1;
        this.elements.sort((x1, x2) -> Float.compare(x1.getY(), x2.getY()));
        elements.get(0).setY(this.bounds.getY());
        for (int i = 1; i < elements.size(); ++i)
            elements.get(i).setY(elements.get(i - 1).getY() + elements.get(i - 1).getHeight() + padding);
        return this;
    }

    public UIContainer fitToContents() {
        if (elements.isEmpty()) {
            this.bounds.setHeight(0);
            this.bounds.setWidth(0);
            return this;
        }
        float minX = Math.min(elements.get(0).getX(), elements.get(0).getX() + elements.get(0).getWidth());
        float minY = Math.min(elements.get(0).getY(), elements.get(0).getY() + elements.get(0).getHeight());
        float maxX = Math.max(elements.get(0).getX(), elements.get(0).getX() + elements.get(0).getWidth());
        float maxY = Math.max(elements.get(0).getY(), elements.get(0).getY() + elements.get(0).getHeight());
        float tempX;
        float tempY;
        for (@SuppressWarnings("unused") UIObject e : elements) {
            tempX = Math.min(elements.get(0).getX(), elements.get(0).getX() + elements.get(0).getWidth());
            tempY = Math.min(elements.get(0).getY(), elements.get(0).getY() + elements.get(0).getHeight());
            if (tempX < minX) minX = tempX;
            if (tempY < minY) minY = tempY;
            tempX = Math.max(elements.get(0).getX(), elements.get(0).getX() + elements.get(0).getWidth());
            tempY = Math.max(elements.get(0).getY(), elements.get(0).getY() + elements.get(0).getHeight());
            if (tempX > maxX) maxX = tempX;
            if (tempY > maxY) maxY = tempY;
        }
        this.bounds.setX(minX);
        this.bounds.setY(minY);
        this.bounds.setWidth(maxX - minX);
        this.bounds.setHeight(maxY - minY);
        return this;
    }

    @Override
    public void move(Vector2D v) {
        this.bounds.move(v);
        elements.forEach(e -> e.move(v));
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
        Vector2D bounds = new Vector2D(this.bounds.getX(), this.bounds.getY());
        Vector2D newBounds = new Vector2D(x - this.bounds.getX(), this.bounds.getY());
        Vector2D moveVector = newBounds.subtract(bounds);
        elements.forEach(e -> e.move(moveVector));
        this.bounds.setX(x);
    }

    @Override
    public void setY(float y) {
        Vector2D bounds = new Vector2D(this.bounds.getX(), this.bounds.getY());
        Vector2D newBounds = new Vector2D(this.bounds.getX(), y - this.bounds.getY());
        Vector2D moveVector = newBounds.subtract(bounds);
        elements.forEach(e -> e.move(moveVector));
        this.bounds.setY(y);
    }

    @Override
    public void setWidth(float w) {
        this.bounds.setWidth(w);
    }

    @Override
    public void setHeight(float h) {
        this.bounds.setHeight(h);
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
        System.out.println(elements.size());
        this.elements.forEach(e -> e.registerOn(uiManager));
    }
}
