package main.java.dev.codenmore.juegorpg.gfx.animation;

import java.awt.image.BufferedImage;

public class StaticAnimation implements Animator {
    private final BufferedImage image;

    public StaticAnimation(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage getCurrentFrame() {
        return image;
    }

    @Override
    public void update() {}
}
