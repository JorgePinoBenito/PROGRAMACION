package main.java.dev.codenmore.juegorpg;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;

import main.java.dev.codenmore.juegorpg.components.Bounds;
import main.java.dev.codenmore.juegorpg.display.Display;
import main.java.dev.codenmore.juegorpg.entities.EntityManager;
import main.java.dev.codenmore.juegorpg.entities.enemies.Enemy;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.AttackingState;
import main.java.dev.codenmore.juegorpg.entities.enemies.state.MovingState;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.gfx.GameCamera;
import main.java.dev.codenmore.juegorpg.input.KeyListenerImpl;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.states.AppStates;
import main.java.dev.codenmore.juegorpg.states.GameState;
import main.java.dev.codenmore.juegorpg.states.MenuState;
import main.java.dev.codenmore.juegorpg.states.State;
import main.java.dev.codenmore.juegorpg.ui.*;
import main.java.dev.codenmore.juegorpg.waves.SingleEnemyTypeWave;
import main.java.dev.codenmore.juegorpg.waves.Wave;
import main.java.dev.codenmore.juegorpg.waves.WaveManager;
import main.java.dev.codenmore.juegorpg.weapons.DebugRangedWeapon;
import main.java.dev.codenmore.juegorpg.weapons.RangedWeapon;
import main.java.dev.codenmore.juegorpg.weapons.WeaponsCache;
import main.java.dev.codenmore.juegorpg.worlds.ImageWorld;

public class App implements Runnable {

    public String title;
    public State<App> currentState;
    private Display display;
    private int width;
    private int height;
    private boolean running = false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private MenuState menuState;
    private GameState gameState;
    private KeyListenerImpl keyListener;
    private MouseListenerImpl mouseListener;


    public App(
        String title,
        int width, int height,
        KeyListenerImpl keyListener, MouseListenerImpl mouseListener
    ) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;
    }

    /**
     * Inicializamos toda la lógica del juego desde aquí. Idealmente moveríamos esta lógica directamente al main.
     */
    private void init() {
        // Configuración del display.
        display = new Display(title, width, height);
        display.getFrame().addMouseListener(mouseListener);
        display.getFrame().addKeyListener(keyListener);
        display.getFrame().addMouseMotionListener(mouseListener);
        display.getCanvas().addMouseListener(mouseListener);
        display.getCanvas().addKeyListener(keyListener);
        display.getCanvas().addMouseMotionListener(mouseListener);
        Assets.init();

        // Creamos el contexto que le va a dar una referencia posterior del juego a las clases que lo necesitan.
        Context context = new Context(this, gameState);

        // Configuración de armas para el juego.
        WeaponsCache weaponsCache = new WeaponsCache();
        weaponsCache.addWeapon(
            new DebugRangedWeapon(null, "debug", 0, 500, 500, null, null)
        );
        weaponsCache.addWeapon(
            new RangedWeapon(null, "Rifle", 100, 20, 80, null, Assets.getStaticAnimation("thompson"))
        );
        weaponsCache.addWeapon(
            new RangedWeapon(null, "Shotgun", 500, 100, 55, null, Assets.getStaticAnimation("shotgun"))
        );
        weaponsCache.addWeapon(
            new RangedWeapon(null, "Revolver", 500, 50, 65, null, Assets.getStaticAnimation("revolver"))
        );
        weaponsCache.addWeapon(
            new RangedWeapon(null, "SkeletonWeapon", 500, 5, 65, null, null)
        );
        weaponsCache.setContext(context);


        // Enemigos.
        Enemy skeleton = new Enemy(
            context,
            0,
            0,
            2,
            weaponsCache.getWeapon("SkeletonWeapon"),
            new MovingState(null, Assets.getDirectionalAnimation("enemy_1_move"), context),
            new AttackingState(null, Assets.getDirectionalAnimation("enemy_1_move"), context)
        );
        Enemy ghost = new Enemy(
            context,
            0,
            0,
            0.5f,
            weaponsCache.getWeapon("SkeletonWeapon"),
            new MovingState(null, Assets.getDirectionalAnimation("enemy_2_move"), context),
            new AttackingState(null, Assets.getDirectionalAnimation("enemy_2_move"), context)
        );
        // Configuración de las oleadas del juego. Hay varias formas de configurar esta clase para tener una experiencia
        // de juego distinta.
        WaveManager waveManager = new WaveManager(
            new Wave[]{
                new SingleEnemyTypeWave(
                    1000 * 10,
                    skeleton,
                    10,
                    1000
                ),
                new SingleEnemyTypeWave(
                    1000 * 30,
                    ghost,
                    3,
                    10000
                )
            }
        );


        // Inicializamos un mundo.
        ImageWorld world = new ImageWorld(
            context,
            new Rectangle[]{
                // Collision boxes
                new Rectangle(32, 96, 273, 225),
                new Rectangle(16, 14, 8, 817),
                new Rectangle(24, 47, 943, 17),
                new Rectangle(352, 16, 8, 226),
                new Rectangle(368, 80, 95, 66),
                new Rectangle(320, 769, 62, 51),
                new Rectangle(400, 590, 8, 243),
                new Rectangle(408, 624, 167, 17),
                new Rectangle(567, 525, 8, 99),
                new Rectangle(737, 592, 62, 48),
                new Rectangle(799, 622, 177, 18),
                new Rectangle(967, 16, 8, 607),
                new Rectangle(768, 128, 161, 193),
                new Rectangle(352, 209, 118, 49),
                new Rectangle(399, 592, 175, 49),
                new Rectangle(798, 593, 176, 47),
                new Rectangle(0, 0, 976, 18),
                new Rectangle(0, 0, 16, 848)
            },
            Assets.world,
            new Point2D.Float(50, 70) // Punto de aparición para el jugador en el mundo.
        );


        GameUI gameUi = new GameUI(
            context,
            new UIManager(mouseListener),
            new FillBar(Bounds.New(20, context.getHeight() - 30, 40, 10), 100, 100, Color.black, Color.red),
            new TextBox(Bounds.New(80, context.getHeight() - 30, 80, 15), ""),
            new TextBox(Bounds.New(170, context.getHeight() - 30, 80, 15), ""),
            new TextBox(Bounds.New(270, context.getHeight() - 30, 80, 15), ""),
            new ImageBox(null, Bounds.New(context.getWidth() - 100, context.getHeight() - 64, 64, 64))
        );

        // Estados de la aplicación.
        gameState = new GameState(
            null,              // Se escoge un jugador desde el menu.
            keyListener,
            mouseListener,
            new EntityManager(),
            world,
            new GameCamera(context, 0, 0),
            waveManager,
            gameUi,
            weaponsCache,
            context
        );
        context.setGameState(gameState);
        menuState = new MenuState(context, mouseListener, weaponsCache);
        currentState = new MenuState(context, mouseListener, weaponsCache);
    }

    public void setState(AppStates state) {
        currentState = switch (state) {
            case MAIN_MENU -> menuState;
            case GAME -> {
                gameState.init();
                yield gameState;
            }
        };
    }

    private void update() {
        keyListener.update();
        mouseListener.update();

        if (currentState != null) {
            State<App> newState = currentState.update(this);
            if (newState != null) {
                currentState = newState;
            }
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Limpiar pantalla.
        g.clearRect(0, 0, width, height);
        // Dibujar aquí.
        if (currentState != null)
            currentState.render(g);

        //End Drawing
        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyListenerImpl getKeyManager() {
        return keyListener;
    }

    public MouseListenerImpl getMouseListener() {
        return mouseListener;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
