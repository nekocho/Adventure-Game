package com.nekocho.adventuregame.model.weapons;

public class Weapon {
    private String name;
    private int damage;
    // Other weapon attributes and methods

    // Constructor
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    // Getters and setters for weapon attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Other weapon methods...
}
