package main.java.dev.codenmore.juegorpg.ui;

import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.utils.Command;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;


public class UIImageButton implements UIObject {

    private final Bounds bounds;
    private final Animator image;
    private final Animator onHoverImage;
    private String text;
    private Command command;

    public UIImageButton(
            Bounds bounds,
            Animator animation,
            Animator onHoverAnimation,
            String text,
            Command command
    ) {
        this.bounds = bounds;
        this.image = animation;
        this.onHoverImage = onHoverAnimation != null ? onHoverAnimation : animation;
        this.command = command;
        this.text = text;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void update() {
        if (this.bounds.isHovering()) {
            onHoverImage.update();
        } else {
            image.update();
        }
        if (this.bounds.isClicked(MouseListenerImpl.MouseButton.LEFT)) {
            this.command.run();
        }
    }

    @Override
    public void render(Graphics g) {
        if (this.bounds.isHovering()) {
            g.drawImage(
                    onHoverImage.getCurrentFrame(),
                    (int) this.bounds.getX(),
                    (int) this.bounds.getY(),
                    (int) this.bounds.getWidth(),
                    (int) this.bounds.getHeight(),
                    null
            );
        } else {
            g.drawImage(
                    image.getCurrentFrame(),
                    (int) this.bounds.getX(),
                    (int) this.bounds.getY(),
                    (int) this.bounds.getWidth(),
                    (int) this.bounds.getHeight(),
                    null
            );
        }
    }

    @Override
    public boolean isWithinBounds(Point point) {
        return this.bounds.isWithinBounds(point);
    }

    @Override
    public void onClick(MouseListenerImpl.MouseButton mouseButton) {
        this.bounds.onClick(mouseButton);
    }

    @Override
    public void setHovering(boolean isHovering) {
        this.bounds.setHovering(isHovering);
    }

    @Override
    public void move(Vector2D v) {
        this.bounds.setX(this.bounds.getX() + (int)v.getX());
        this.bounds.setY(this.bounds.getY() + (int)v.getY());
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
    }
}
