package main.java.dev.codenmore.juegorpg.weapons;

import main.java.dev.codenmore.juegorpg.Context;

import java.util.HashMap;

/**
 * Todas las armas a ser utilizadas en el juego deben estar aquí. Al invocar "getWeapon" se retorna una copia del
 * prototipo de cada arma.
 */
public class WeaponsCache {
    private final HashMap<String, Weapon> prototypes = new HashMap<>();

    public void addWeapon(Weapon weapon) {
        prototypes.put(weapon.name, weapon);
    }

    public Weapon getWeapon(String name) {
        return this.prototypes.get(name).copy();
    }

    public void setContext(Context context) {
        prototypes.forEach((k, v) -> v.setContext(context));
    }
}
