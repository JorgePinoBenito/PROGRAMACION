package main.java.dev.codenmore.juegorpg.entities.statics;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.tiles.TileType;

import java.awt.image.BufferedImage;

public class Tree extends StaticEntity {

	public Tree(Context context, float x, float y) {
		super(context, x, y, TileType.TILEWIDTH /2, TileType.TILEHEIGHT / 2, TileType.TILEWIDTH, TileType.TILEHEIGHT*2, Assets.getStaticAnimation("tree"));
	}

    @Override
    public void update() {

    }

    @Override
    protected BufferedImage getCurrentFrame() {
        return null;
    }

    @Override
	public void kill(){
	}
}
