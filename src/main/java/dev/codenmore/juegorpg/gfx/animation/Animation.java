package main.java.dev.codenmore.juegorpg.gfx.animation;

import java.awt.image.BufferedImage;

public class Animation implements Animator {
    private int speed;
    private int index;
    private long lastTime, timer;
    private BufferedImage[] frames;
    private boolean shouldLoop = true;

    /**
     * Este constructor de copia devuelve una copia superficial de las imágenes almacenadas en búfer en su interior.
     */
    public Animation(Animation other) {
        this.speed = other.speed;
        this.frames = other.frames;
        this.index = other.index;
        this.lastTime = other.lastTime;
        this.timer = other.timer;
        this.shouldLoop = other.shouldLoop;
    }

    public Animation(int speed, BufferedImage[] frames, boolean shouldLoop) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        this.shouldLoop = shouldLoop;
    }

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public static Animation NewStatic(BufferedImage image) {
        return new Animation(0, new BufferedImage[]{image});
    }

    @Override
    public void update() {
        if (speed == 0) return;
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            timer = 0;
            if (index+1 >= frames.length) {
                if (shouldLoop)
                    index = 0;
            } else {
                index++;
            }
        }
    }

    @Override
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
