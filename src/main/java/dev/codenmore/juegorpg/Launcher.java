package main.java.dev.codenmore.juegorpg;

import main.java.dev.codenmore.juegorpg.input.KeyListenerImpl;
import main.java.dev.codenmore.juegorpg.input.MouseListenerImpl;
import main.java.dev.codenmore.juegorpg.weapons.DebugRangedWeapon;
import main.java.dev.codenmore.juegorpg.weapons.MeleeWeapon;
import main.java.dev.codenmore.juegorpg.weapons.RangedWeapon;
import main.java.dev.codenmore.juegorpg.weapons.WeaponsCache;

import java.awt.*;

public class Launcher {

    public static void main(String[] args) {
        App app = new App(
            "Military RPG",
            640, 480,
            new KeyListenerImpl(),
            new MouseListenerImpl()
        );
        app.start();
    }
}


