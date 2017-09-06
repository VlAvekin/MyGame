package com.vladaavekin.Game.Model;

public class Unit {

    private String name;

    private int hp;         // Здоровье

    private int dameg;      // Урон

    private int stamina;    // Выносливость

    private int armor;      // Броня

    private int speed;      // скорость

    private int backpack;   // рюбзак

    public Unit(final String name,
                final int hp,
                final int dameg,
                final int stamina,
                final int armor,
                final int speed,
                final int backpack) {
        this.name = name;
        this.hp = hp;
        this.dameg = dameg;
        this.stamina = stamina;
        this.armor = armor;
        this.speed = speed;
        this.backpack = backpack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDameg() {
        return dameg;
    }

    public void setDameg(int dameg) {
        this.dameg = dameg;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBackpack() {
        return backpack;
    }

    public void setBackpack(int backpack) {
        this.backpack = backpack;
    }
}
