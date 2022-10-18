package main.java.dev.codenmore.juegorpg.input;

import main.java.dev.codenmore.juegorpg.ui.ClickListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MouseListenerImpl implements MouseListener, MouseMotionListener {
    private final ArrayList<Clickable> subjects = new ArrayList<>();
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private int mouseX, mouseY;

    public void update() {
        subjects.forEach(this::notify);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            this.isLeftPressed = true;
        if (e.getButton() == MouseEvent.BUTTON3)
            this.isRightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            this.isLeftPressed = false;
        else if (e.getButton() == MouseEvent.BUTTON3)
            this.isRightPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void addListener(Clickable clickable) {
        this.subjects.add(clickable);
    }

    public void removeListener(Clickable clickable) {
        this.subjects.remove(clickable);
    }

    public void clear() {
        this.subjects.clear();
    }

    private void notify(Clickable clickable) {
        boolean isHovering = clickable.isWithinBounds(new Point(mouseX, mouseY));
        clickable.setHovering(isHovering);
        if (isHovering) {
            if (isLeftPressed) clickable.onClick(MouseButton.LEFT);
            if (isRightPressed) clickable.onClick(MouseButton.RIGHT);
        }
    }

    public boolean mouseIsPressed(MouseButton mouseButton) {
        return switch (mouseButton) {
            case LEFT -> isLeftPressed;
            case RIGHT -> isRightPressed;
        };
    }

    public enum MouseButton {
        LEFT,
        RIGHT,
    }
}
