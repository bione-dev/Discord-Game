package com.bot.game;

public class Tool {
    private int level;
    private int upgradeCost;

    public Tool() {
        this.level = 1;
        this.upgradeCost = 100;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void upgrade() {
        level++;
        upgradeCost += 50;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }
}
