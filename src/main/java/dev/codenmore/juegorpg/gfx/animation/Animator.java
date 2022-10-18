package main.java.dev.codenmore.juegorpg.gfx.animation;

import java.awt.image.BufferedImage;

public interface Animator {
    BufferedImage getCurrentFrame();
    void update();
}

