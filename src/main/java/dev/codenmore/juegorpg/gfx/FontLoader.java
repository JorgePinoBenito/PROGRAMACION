package main.java.dev.codenmore.juegorpg.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    public static Font loadFontFromPath(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static Font loadFrontFromInputStream(String path, float size) {
        try {
            InputStream is = FontLoader.class.getResourceAsStream(path);
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static Font loadFontFromAny(String path, float size) {
        Font fromIs = loadFrontFromInputStream(path, size);
        if (fromIs != null) {
            return fromIs;
        } else {
            return loadFontFromPath("res/" + path, size);
        }
    }
}
