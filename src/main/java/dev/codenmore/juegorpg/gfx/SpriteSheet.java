package main.java.dev.codenmore.juegorpg.gfx;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpriteSheet {

    private final BufferedImage sheet;
    private final JSONObject spec;
    private final String sheetPath;
    private final String specPath;
    private final Pattern animationStripRegex = Pattern.compile(".*strip(\\d+).*");

    public SpriteSheet(BufferedImage sheet, JSONObject spec, String sheetPath, String specPath) {
        this.sheet = sheet;
        this.spec = spec;
        this.sheetPath = sheetPath;
        this.specPath = specPath;
    }

    public static SpriteSheet fromPath(String sheetPath, String specPath) {
        try {
            String s = new String(Files.readAllBytes(Paths.get(specPath)));
            JSONObject spec = new JSONObject(s);
            BufferedImage sheet = ImageIO.read(new File(sheetPath));
            return new SpriteSheet(sheet, spec, sheetPath, specPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static SpriteSheet fromInputStream(String sheetPath, String specPath) {
        try {
            InputStream inputStreamObject = SpriteSheet.class.getResourceAsStream(specPath);
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(
                inputStreamObject,
                StandardCharsets.UTF_8
            ));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject spec = new JSONObject(responseStrBuilder.toString());


            InputStream is1 = SpriteSheet.class.getResourceAsStream(sheetPath);
            BufferedImage sheet = ImageIO.read(is1);
            return new SpriteSheet(sheet, spec, sheetPath, specPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static SpriteSheet fromAny(String sheetPath, String specPath) {
        SpriteSheet fromIs = fromInputStream(sheetPath, specPath);
        if (fromIs != null) {
            return fromIs;
        } else {
            return fromPath("res/"+sheetPath, "res/"+specPath);
        }
    }

    public String getSheetPath() {
        return sheetPath;
    }

    public String getSpecPath() {
        return specPath;
    }

    public BufferedImage crop(int x, int y, int widht, int height) {
        return sheet.getSubimage(x, y, widht, height);
    }

    public BufferedImage[] getSprite(String name) {
        JSONObject spriteInfo = spec.getJSONObject("frames").getJSONObject(name).getJSONObject("frame");
        int x = spriteInfo.getInt("x");
        int y = spriteInfo.getInt("y");
        int w = spriteInfo.getInt("w");
        int h = spriteInfo.getInt("h");
        Matcher matcher = animationStripRegex.matcher(name);
        BufferedImage[] images;
        if (matcher.matches()) {
            int nFrames = Integer.parseInt(matcher.group(1));
            int sepW = w / nFrames;
            images = new BufferedImage[nFrames];
            for (int i = 0; i < nFrames; ++i)
                images[i] = crop(x + i * sepW, y, sepW, h);
        } else {
            images = new BufferedImage[1];
            images[0] = crop(x, y, w, h);
        }
        return images;
    }
}
