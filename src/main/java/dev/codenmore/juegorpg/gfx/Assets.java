package main.java.dev.codenmore.juegorpg.gfx;

import main.java.dev.codenmore.juegorpg.gfx.animation.Animation;
import main.java.dev.codenmore.juegorpg.gfx.animation.DirectionalAnimation;
import main.java.dev.codenmore.juegorpg.gfx.animation.StaticAnimation;
import main.java.dev.codenmore.juegorpg.worlds.IWorld;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Singleton con los assets gráficos y texturas del juego. Debido a lo simple del juego un singleton es suficiente.
 */
public class Assets {
    public static Font mariobros28;
    public static Animation btnStart;
    public static BufferedImage world;

    public static HashMap<String, DirectionalAnimation> directionalAnimations = new HashMap<>();
    public static HashMap<String, StaticAnimation> staticAnimations = new HashMap<>();
    public static HashMap<String, Animation> animations = new HashMap<>();

    private Assets() {}

    public static StaticAnimation getStaticAnimation(String name) {
        return staticAnimations.get(name);
    }

    /**
     * Obtiene una copia del prototipo para reusar solamente la imagen. El resto del estado de la animacion es
     * copiado por valor.
     */
    public static DirectionalAnimation getDirectionalAnimation(String name) {
        return new DirectionalAnimation(directionalAnimations.get(name));
    }

    public static Animation getAnimation(String name) {
        return new Animation(animations.get(name));
    }

    public static void init() {
        mariobros28 = FontLoader.loadFontFromAny("/fonts/SuperMario256.ttf", 28);
        SpriteSheet sheet = SpriteSheet.fromAny("/textures/spritesheet.png", "/textures/spritesheet.json");

        btnStart = new Animation(
            200,
            new BufferedImage[]{
                sheet.getSprite("start_btn.png")[0],
                sheet.getSprite("start_btn_2.png")[0]
            }
        );

        directionalAnimations.put("player_move", new DirectionalAnimation(
            new Animation(150, sheet.getSprite("SMS_Soldier_RUN_NORTH_strip4.png")),
            new Animation(150, sheet.getSprite("SMS_Soldier_RUN_SOUTH_strip4.png")),
            new Animation(150, sheet.getSprite("SMS_Soldier_RUN_WEST_strip4.png")),
            new Animation(150, sheet.getSprite("SMS_Soldier_RUN_EAST_strip4.png"))
        ));
        directionalAnimations.put("player_idle", new DirectionalAnimation(
            new Animation(100, sheet.getSprite("SMS_Soldier_IDLE_NORTH_strip4.png")),
            new Animation(100, sheet.getSprite("SMS_Soldier_SOUTH_strip4.png")),
            new Animation(100, sheet.getSprite("SMS_Soldier_IDLE_WEST_strip4.png")),
            new Animation(100, sheet.getSprite("SMS_Soldier_IDLE_EAST_strip4.png"))
        ));
        directionalAnimations.put("player_attack", new DirectionalAnimation(
            new Animation(150, sheet.getSprite("SMS_Soldier_CROUCHRIFLE_NORTH.png")),
            new Animation(150, sheet.getSprite("SMS_Soldier_CROUCHRIFLE_SOUTH.png")),
            new Animation(150, sheet.getSprite("SMS_Soldier_CROUCHRIFLE_WEST.png")),
            new Animation(150, sheet.getSprite("SMS_Soldier_CROUCHRIFLE_EAST.png"))
        ));
        directionalAnimations.put("enemy_1_move", new DirectionalAnimation(
            new Animation(150, sheet.getSprite("enemy_1_move_north_strip4.png")),
            new Animation(150, sheet.getSprite("enemy_1_move_south_strip4.png")),
            new Animation(150, sheet.getSprite("enemy_1_move_west_strip4.png")),
            new Animation(150, sheet.getSprite("enemy_1_move_east_strip4.png"))
        ));
        directionalAnimations.put("enemy_2_move", new DirectionalAnimation(
            new Animation(150, sheet.getSprite("enemy_2_move_north_strip4.png")),
            new Animation(150, sheet.getSprite("enemy_2_move_south_strip4.png")),
            new Animation(150, sheet.getSprite("enemy_2_move_west_strip4.png")),
            new Animation(150, sheet.getSprite("enemy_2_move_east_strip4.png"))
        ));
        directionalAnimations.put("dying_state", new DirectionalAnimation(
            new Animation(200, sheet.getSprite("SMS_Soldier_DEATH_NORTH_strip4.png"),false),
            new Animation(200, sheet.getSprite("SMS_Soldier_DEATH_SOUTH_strip4.png"), false),
            new Animation(200, sheet.getSprite("SMS_Soldier_DEATH_WEST_strip4.png"), false),
            new Animation(200, sheet.getSprite("SMS_Soldier_DEATH_EAST_strip4.png"), false)
        ));

        animations.put("gunner_frame", new Animation(90, sheet.getSprite("SMS_Soldier_AVATAR_ANGRYTALK_strip4.png")));
        animations.put("sniper_frame", new Animation(90, sheet.getSprite("SMS_Soldier_AVATAR_BLINK_strip4.png")));
        animations.put("doctor_frame", new Animation(90, sheet.getSprite("SMS_Soldier_AVATAR_LAUGH_strip4.png")));

        staticAnimations.put("wood", new StaticAnimation(sheet.getSprite("wood.png")[0]));
        staticAnimations.put("dirt", new StaticAnimation(sheet.getSprite("dirt.png")[0]));
        staticAnimations.put("grass", new StaticAnimation(sheet.getSprite("grass.png")[0]));
        staticAnimations.put("stone", new StaticAnimation(sheet.getSprite("stone.png")[0]));
        staticAnimations.put("tree", new StaticAnimation(sheet.getSprite("tree.png")[0]));
        staticAnimations.put("rock", new StaticAnimation(sheet.getSprite("rock.png")[0]));
        staticAnimations.put("thompson", new StaticAnimation(sheet.getSprite("M1A1 Thompson.png")[0]));
        staticAnimations.put("revolver", new StaticAnimation(sheet.getSprite("revolver.png")[0]));
        staticAnimations.put("shotgun", new StaticAnimation(sheet.getSprite("shotgun.png")[0]));

        world = ImageLoader.loadIsOrFile("/worlds/world2.png");
    }
}
