package org.example.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Represents the inventory of the player character in the game.
 */
public class Inventory {

    /** The maximum capacity of the inventory. */
    /** The count of gold in the inventory. */
    public int gold_count = 0;
    public int healthPotionCount = 0;
    public int mushroomCount = 0;
    public int waterCount = 0;
    /**
     * Constructs a new Inventory object with the specified initial gold count.
     *
     * @param gold_count the initial count of gold in the inventory
     */
    public Inventory(int gold_count, int healthPotionCount, int mushroomCount, int waterCount) {
        this.gold_count = gold_count;
        this.healthPotionCount = healthPotionCount;
        this.mushroomCount = mushroomCount;
        this.waterCount = waterCount;
    }

    public int getGold_count() {
        return gold_count;
    }

    public int getHealthPotionCount() {
        return healthPotionCount;
    }

    public int getMushroomCount() {
        return mushroomCount;
    }

    public int getWaterCount() {
        return waterCount;
    }
}
