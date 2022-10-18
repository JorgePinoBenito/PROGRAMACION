package main.java.dev.codenmore.juegorpg.states;

import main.java.dev.codenmore.juegorpg.App;
import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.entities.player.Doctor;
import main.java.dev.codenmore.juegorpg.entities.player.Player;
import main.java.dev.codenmore.juegorpg.gfx.GameCamera;
import main.java.dev.codenmore.juegorpg.input.KeyListenerImpl;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.ui.FillBar;
import main.java.dev.codenmore.juegorpg.ui.GameUI;
import main.java.dev.codenmore.juegorpg.ui.TextBox;
import main.java.dev.codenmore.juegorpg.ui.UIManager;
import main.java.dev.codenmore.juegorpg.waves.WaveManager;
import main.java.dev.codenmore.juegorpg.weapons.WeaponsCache;
import main.java.dev.codenmore.juegorpg.worlds.IWorld;

import java.awt.*;

/**
 * GameState contiene la lógica de negocio 
 * (codificar las reglas de negocio del mundo real que determinan cómo la información puede ser creada, almacenada y cambiada) 
 * del juego.
 */
public class GameState implements State<App> {
    private Player player;
    private IWorld world;
    private int score = 0;
    private WaveManager waveManager;
    private EntityManager entityManager;
    private GameCamera camera;
    private KeyListenerImpl keyboardListener;
    private MouseListenerImpl mouseListener;
    private GameUI ui;
    private WeaponsCache weaponsCache;
    private boolean gameIsOver = false;
    private Context context;

    public GameState(
        Player player,
        KeyListenerImpl keyboardListener,
        MouseListenerImpl mouseListener,
        EntityManager entityManager,
        IWorld world,
        GameCamera gameCamera,
        WaveManager waveManager,
        GameUI ui,
        WeaponsCache weaponsCache,
        Context context
    ) {
        this.keyboardListener = keyboardListener;
        this.mouseListener = mouseListener;
        this.entityManager = entityManager;
        this.player = player;
        this.world = world;
        this.camera = gameCamera;
        this.waveManager = waveManager;
        this.weaponsCache = weaponsCache;
        this.ui = ui;
        this.context = context;
    }

    public void init() {
        entityManager.addEntity(player);
        world.init(entityManager);
        player.setX(world.getPlayerSpawnX());
        player.setY(world.getPlayerSpawnY());
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    @Override
    public State<App> update(App app) {
        camera.centerOnEntity(player);
        waveManager.spawn(world, entityManager);
        world.update();
        entityManager.update();
        // post render
        ui.update();

        // end condition
        if (!player.isActive()) {
            System.out.println("You lose");
            System.exit(0);
        } else if (waveManager.isDone() && entityManager.getEntities().size() == 1) {
            System.out.println("You win");
            System.exit(0);
        }

        return null;
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        entityManager.render(g);
        waveManager.render(g);
        ui.render(g);
    }

    public GameCamera getGameCamera() {
        return this.camera;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public IWorld getWorld() {
        return this.world;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
