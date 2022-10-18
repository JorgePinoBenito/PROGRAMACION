package main.java.dev.codenmore.juegorpg.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

    public static BufferedImage loadInputStream(String s) {
        try {
            return ImageIO.read(ImageLoader.class.getResourceAsStream(s));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static BufferedImage loadIsOrFile(String s) {
        BufferedImage fromIs = loadInputStream(s);
        if (fromIs != null) {
            return fromIs;
        } else {
            return loadImage("res/"+s);
        }
    }
}
