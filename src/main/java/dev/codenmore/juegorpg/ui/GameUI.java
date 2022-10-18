package main.java.dev.codenmore.juegorpg.ui;

import main.java.dev.codenmore.juegorpg.Context;

import java.awt.*;

public class GameUI {
    private final Context context;
    private final UIManager uiManager;
    private final FillBar playerHealthBar;
    private final TextBox waveTimer;
    private final TextBox waveCounter;
    private final TextBox scoreCounter;
    private final ImageBox weaponFrame;

    public GameUI(
        Context context, UIManager uiManager, FillBar playerHealthBar, TextBox waveTimer,
        TextBox waveCounter,
        TextBox scoreCounter,
        ImageBox weaponFrame
    ) {
        this.context = context;
        this.uiManager = uiManager;
        this.playerHealthBar = playerHealthBar;
        this.uiManager.addObject(playerHealthBar);
        this.waveTimer = waveTimer;
        this.uiManager.addObject(waveTimer);
        this.waveCounter = waveCounter;
        this.uiManager.addObject(waveCounter);
        this.scoreCounter = scoreCounter;
        this.uiManager.addObject(scoreCounter);
        this.weaponFrame = weaponFrame;
        this.uiManager.addObject(weaponFrame);
    }


    public void render(Graphics g) {
        uiManager.render(g);
    }

    public void update() {
        uiManager.update();

        playerHealthBar.setCurrentUnits(context.getGameState().getPlayer().getHealth());

        long timer = context.getGameState().getWaveManager().getCurrentTimer();
        if (timer < 0 ) {
            timer = 0;
        }
        waveTimer.setString(String.format("Time left: %d:%d", (int) (timer / 60000), timer / 1000 % 60));

        waveCounter.setString(String.format("Wave %d", context.getGameState().getWaveManager().getCurrentWave()));

        scoreCounter.setString(String.format("Score %d", context.getGameState().getScore()));

        weaponFrame.setImage(context.getGameState().getPlayer().getWeapon().getFrame());
    }
}
