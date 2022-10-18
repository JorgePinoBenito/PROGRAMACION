package main.java.dev.codenmore.juegorpg.ui;

import java.awt.Graphics;
import java.util.ArrayList;

import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;

public class UIManager {
    private final ArrayList<UIObject> objects;
    private final MouseListenerImpl mouseListener;

    public UIManager(MouseListenerImpl mouseListener) {
        objects = new ArrayList<>();
        this.mouseListener = mouseListener;
    }

    public void update() {
        objects.forEach(UIObject::update);
    }

    public void render(Graphics g) {
        objects.forEach(o -> o.render(g));
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void addObject(UIObject o) {
        mouseListener.addListener(o);
        if (!objects.contains(o)) objects.add(o);
    }

    public void removeObject(UIObject o) {
        mouseListener.removeListener(o);
        objects.remove(o);
    }

    public void clear() {
        objects.forEach(mouseListener::removeListener);
        this.objects.clear();
    }
}
