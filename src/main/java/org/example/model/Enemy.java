package org.example.model;

import org.example.view.FilePath;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Represents an enemy unit in the game.
 */
public class Enemy extends Unit {


    /** The hitbox of the enemy (not serialized). */
    public transient Rectangle hitbox;
    /** Flag indicating if the enemy is grounded. */
    public boolean isGrounded = false;
    /** A random number generator for the enemy's behavior. */
    public transient Random random;

    /**
     * Constructs a new Enemy object with the specified parameters.
     *
     * @param type        the type of the enemy
     * @param health      the health points of the enemy
     * @param attack      the attack power of the enemy
     * @param speed       the movement speed of the enemy
     * @param coordinates the initial coordinates of the enemy
     * @param isAlive     the status indicating if the enemy is alive
     */
    public Enemy(String type, int health,int currentHealth, int attack, int speed, Coordinates coordinates, boolean isAlive) {
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.speed = speed;
        this.coordinates = coordinates;
        this.hitbox = new Rectangle(coordinates.x, coordinates.y, 64, 64);
        this.isAlive = isAlive;
        this.dir = 1;
        this.revers = -64;
        this.current_health = currentHealth;
        this.random = new Random();
    }

    /**
     * Moves the enemy unit.
     *
     * @param hunter the player's character
     */
    public void move(Hunter hunter) {
        hitbox = new Rectangle(coordinates.x + 20, coordinates.y, 25, 64);


            if (!isGrounded) {
                coordinates.y += Math.min(5, 700 - coordinates.y);
            }
            anim_tickY = 1;
            animation_speed++;
            if (animation_speed > 30) {
                for (int i = 0; i < 8; i++) {
                    if (anim_tickX >= 7) {
                        anim_tickX = 0;
                    }
                    anim_tickX++;
                }
                animation_speed = 0;
            }

            isGrounded = false;
            collision = false;

    }

    /**
     * Initiates an attack action by the enemy.
     */
    public void attack() {
        // Implementation of the attack action goes here
    }

    /**
     * Performs actions when the enemy unit dies.
     */
    public void die() {
        isAlive = false;
    }



    /**
     * Gets the type of the enemy.
     *
     * @return the type of the enemy
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the x-coordinate of the enemy.
     *
     * @return the x-coordinate of the enemy
     */
    public int getX() {
        return coordinates.x;
    }

    /**
     * Gets the y-coordinate of the enemy.
     *
     * @return the y-coordinate of the enemy
     */
    public int getY() {
        return coordinates.y;
    }

}
