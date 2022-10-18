package main.java.dev.codenmore.juegorpg.entities.statics;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.tiles.TileType;

import java.awt.*;

public class Rock extends StaticEntity {

	public Rock(Context context, float x, float y) {
		super(context, x, y, TileType.TILEWIDTH, TileType.TILEHEIGHT, Assets.getStaticAnimation("rock"));
	}

	@Override
	public void kill(){
	}

    @Override
    public void update() {
    }

    @Override
	public void render(Graphics g) {
		g.drawImage(Assets.getStaticAnimation("rock").getCurrentFrame(), (int) (x - context.getGameCamera().getX()), (int) (y - context.getGameCamera().getY()), width, height, null);
	}

}

