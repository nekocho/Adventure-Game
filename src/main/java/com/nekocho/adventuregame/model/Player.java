package com.nekocho.adventuregame.model;

import com.nekocho.adventuregame.model.weapons.Weapon;

import org.springframework.stereotype.Component;


@Component
public class Player{

    public int hp;
    public int coin;
    public Weapon currentWeapon;
    // Other player attributes and methods

    // No-argument constructor
    // Default constructor
    public Player() {
        // Initialize default values
        this.hp = 10; // Default HP
        this.coin = 100; // Default coins
        this.currentWeapon = null; // Default weapon (null or any default weapon you want)
    }

    // Constructor with parameters
    public Player(int hp, int coin, Weapon currentWeapon) {
        this.hp = hp;
        this.coin = coin;
        this.currentWeapon = currentWeapon;
    }

    // Getters and setters for player attributes
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
}
