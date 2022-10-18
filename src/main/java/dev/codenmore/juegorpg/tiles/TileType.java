package main.java.dev.codenmore.juegorpg.tiles;

import main.java.dev.codenmore.juegorpg.gfx.animation.Animator;

import java.awt.*;

/**
 * @deprecated
 */
public class TileType {
	public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;
    private final Animator texture;
    private final boolean isSolid;

	public TileType(Animator texture, boolean isSolid) {
		this.texture = texture;
        this.isSolid = isSolid;
	}
	
	public void update(){
        this.texture.update();
	}

	public void render(Graphics g, int x, int y){
		g.drawImage(texture.getCurrentFrame(), x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
        return this.isSolid;
	}

    public Rectangle getBounds(int x, int y) {
        return new Rectangle(x*TILEWIDTH, y*TILEHEIGHT, TILEWIDTH, TILEHEIGHT);
    }
}
