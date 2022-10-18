package main.java.dev.codenmore.juegorpg.entities.statics;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.Entity;
import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;

import java.awt.image.BufferedImage;

public abstract class StaticEntity extends Entity {
    Animator animation;

    public StaticEntity(Context context, float x, float y, int width, int height, Animator animation) {
        super(context, x, y, width, height);
        this.animation = animation;
    }

    public StaticEntity(Context context, float x, float y, int width, int height, int spriteWidth, int spriteHeight, Animator animation) {
        super(context, x, y, width, height, spriteWidth, spriteHeight);
        this.animation = animation;
    }

    @Override
    public void update() {
        this.animation.update();
    }

    @Override
    protected BufferedImage getCurrentFrame() {
        return this.animation.getCurrentFrame();
    }
}
