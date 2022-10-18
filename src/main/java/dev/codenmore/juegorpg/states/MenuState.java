package main.java.dev.codenmore.juegorpg.states;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.App;
import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.entities.enemies.Enemy;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.AttackingState;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.MovingState;
import main.java.dev.codenmore.juegorpg.entities.player.Doctor;
import main.java.dev.codenmore.juegorpg.entities.player.Gunner;
import main.java.dev.codenmore.juegorpg.entities.player.Player;
import main.java.dev.codenmore.juegorpg.entities.player.Sniper;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.ui.*;
import main.java.dev.codenmore.juegorpg.waves.SingleEnemyTypeWave;
import main.java.dev.codenmore.juegorpg.waves.Wave;
import main.java.dev.codenmore.juegorpg.waves.WaveManager;
import main.java.dev.codenmore.juegorpg.weapons.*;
import main.java.dev.codenmore.juegorpg.worlds.ImageWorld;

/**
 * Estado del juego que controla las funciones del menu principal.
 */
public class MenuState implements State<App> {

    private final UIManager uiManager;
    private Player characterChoice;
    private boolean shouldTransition = false;

    public MenuState(Context context, MouseListenerImpl mouseListener, WeaponsCache weaponsCache) {
        uiManager = new UIManager(mouseListener);
        UIContainer panel = new UIContainer(new ArrayList<UIObject>(Arrays.asList(
            UIContainer.NewDefault(new ArrayList<>(Arrays.asList(
                    new UIImageButton(
                        Bounds.New(0, 0, 128, 128),
                        Assets.getAnimation("sniper_frame"),
                        null,
                        "Sniper",
                        () -> {
                            shouldTransition = true;
                            context.getGameState().setPlayer(new Sniper(context, 0, 0, weaponsCache.getWeapon("Rifle")));
                        }
                    ),
                    new TextBox(Bounds.New(2, 0, 80, 40), "Sniper")
                ))
            ).distributeHorizontally(),
            UIContainer.NewDefault(new ArrayList<>(Arrays.asList(
                    new UIImageButton(
                        Bounds.New(0, 0, 128, 128),
                        Assets.getAnimation("gunner_frame"),
                        null,
                        "Gunner",
                        () -> {
                            shouldTransition = true;
                            context.getGameState().setPlayer(new Gunner(context, 0, 0, weaponsCache.getWeapon("Revolver")));
                        }
                    ),
                    new TextBox(Bounds.New(3, 0, 80, 40), "Gunner")
                ))
            ).distributeHorizontally(),
            UIContainer.NewDefault(new ArrayList<>(Arrays.asList(
                    new UIImageButton(
                        Bounds.New(0, 0, 128, 128),
                        Assets.getAnimation("doctor_frame"),
                        null,
                        "Doctor",
                        () -> {
                            shouldTransition = true;
                            context.getGameState().setPlayer(new Doctor(context, 0, 0, weaponsCache.getWeapon("Shotgun")));
                        }
                    ),
                    new TextBox(Bounds.New(2, 0, 80, 40), "Doctor")
                ))
            ).distributeHorizontally()
        )), Bounds.New(0, 0, context.getWidth(), context.getHeight()))
            .alignHorizontally()
            .distributeVertically();
        panel.registerOn(uiManager);
    }

    @Override
    public State<App> update(App app) {
        uiManager.update();
        if (shouldTransition) {
            shouldTransition = false; // Reset flag
            app.setState(AppStates.GAME);
        }
        return null;
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}