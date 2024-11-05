package com.bot.game;

public class Player {
    private int level;
    private int prestigeLevel;
    private int coins;
    private Backpack backpack;
    private Tool tool;

    public Player() {
        this.level = 1;
        this.prestigeLevel = 0;
        this.coins = 0;
        this.backpack = new Backpack();
        this.tool = new Tool();
    }

    public int getLevel() {
        return level;
    }

    public int getPrestigeLevel() {
        return prestigeLevel;
    }

    public void setPrestigeLevel(int prestigeLevel) {
        this.prestigeLevel = prestigeLevel;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Tool getTool() {
        return tool;
    }

    public void resetStatsForPrestige() {
        this.level = 1;
        this.coins = 0;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
