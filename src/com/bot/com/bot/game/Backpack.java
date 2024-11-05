package com.bot.game;

public class Backpack {
    private int totalValue;

    public int calculateTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public void clear() {
        this.totalValue = 0;
    }
}
