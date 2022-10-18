package main.java.dev.codenmore.juegorpg.entities.player;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.weapons.Weapon;

public class Sniper extends Player {

    public Sniper(Context context, float x, float y, Weapon startingWeapon) {
        super(
            context,
            x,
            y,
            30,
            30,
            "Sniper",
            startingWeapon
        );
    }
}