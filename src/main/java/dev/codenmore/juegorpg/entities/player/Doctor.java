package main.java.dev.codenmore.juegorpg.entities.player;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.weapons.Weapon;

public class Doctor extends Player {
    public Doctor(Context context, float x, float y, Weapon startingWeapon) {
        super(
            context,
            x,
            y,
            "Doctor",
            startingWeapon
        );
    }
}