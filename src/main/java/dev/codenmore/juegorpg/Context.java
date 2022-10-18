package main.java.dev.codenmore.juegorpg;

import main.java.dev.codenmore.juegorpg.gfx.GameCamera;
import main.java.dev.codenmore.juegorpg.input.KeyListenerImpl;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.states.GameState;

public class Context {
	
	private App app;
    private GameState gameState;
	
	public Context(App app, GameState gameState) {
		this.app = app;
        this.gameState = gameState;
	}

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

	public GameCamera getGameCamera(){
		return gameState.getGameCamera();
	}
	
	public KeyListenerImpl getKeyListener(){
		return app.getKeyManager();
	}
	
	public MouseListenerImpl getMouseListener(){
		return app.getMouseListener();
	}

    public GameState getGameState() {
        return gameState;
    }
	
	public int getWidth(){
		return app.getWidth();
	}
	
	public int getHeight(){
		return app.getHeight();
	}
}