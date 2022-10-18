package main.java.dev.codenmore.juegorpg.entities.player;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.gfx.Assets;
import main.java.dev.codenmore.juegorpg.weapons.Weapon;

public class Gunner extends Player {
	public Gunner(Context context, float x, float y, Weapon startingWeapon) {
        super(
            context,
            x,
            y,
            "Gunner",
            startingWeapon
        );
    }
}

