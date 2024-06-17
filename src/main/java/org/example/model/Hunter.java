package org.example.model;

import org.example.view.MovementState;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents the player-controlled character in the game.
 */
public class Hunter extends Unit {

    /** The horizontal shift of the player character. */
    public int Xshift = 0;
    /** The hitbox of the player character (not serialized). */
    transient public Rectangle hitbox;
    /** The inventory of the player character. */
    public Inventory inventory;
    /** Flag indicating if the player character is grounded. */
    transient public boolean isgrounded = false;
    public transient boolean wasAttacked = false;

    /**
     * Constructs a new Hunter object with the specified parameters.
     *
     * @param Xshift      the horizontal shift of the player character
     * @param health      the health points of the player character
     * @param attack      the attack power of the player character
     * @param speed       the movement speed of the player character
     * @param coordinates the initial coordinates of the player character
     * @param inventory   the inventory of the player character
     */
    public Hunter(int Xshift, int health,int currentHealth, int attack, int speed, Coordinates coordinates, Inventory inventory) {
        this.health = health;
        this.attack = attack;
        this.speed = speed;
        this.inventory = inventory;
        this.dir = 1;
        this.revers = -90;
        this.coordinates = coordinates;
        this.state = MovementState.IDLE;
        this.Xshift = Xshift;
        this.current_health = currentHealth;
        this.hitbox = new Rectangle(coordinates.x, coordinates.y, 50, 70);
    }

    /**
     * Moves the player character based on its state.
     */
    public void move() {
        hitbox = new Rectangle(coordinates.x - 60 + Xshift, coordinates.y, 30, 70);
        if(coordinates.y+70>700){
            isAlive=false;
        }
        if (!isgrounded) {
            coordinates.y += Math.min(5, 700 - coordinates.y);
        }

        switch (state) {
            case IDLE:
                if (isgrounded) {
                    anim_tickX = 0;
                    anim_tickY = 0;
                    break;
                }
                if (!isgrounded) {
                    anim_tickY = 3;
                    for (int i = 1; i < 5; i++) {
                        if (animation_speed >= 40) {
                            animation_speed = 0;
                            if (anim_tickX >= 4)
                                anim_tickX = 1;
                            anim_tickX += 1;
                        }
                        animation_speed += 1;
                    }
                    break;
                }
            case MOVING:
                anim_tickY = 3;
                if (coordinates.x < 60)
                    coordinates.x = 60;

                for (int i = 1; i < 5; i++) {
                    if (!isgrounded)
                        break;
                    if (animation_speed >= 90 / speed) {
                        animation_speed = 0;
                        if (anim_tickX >= 4)
                            anim_tickX = 0;
                        anim_tickX += 1;
                    }
                    animation_speed += 1;
                }

                Xshift += dir * speed;
                if (Xshift < 0) {
                    Xshift = 0;
                    coordinates.x += dir * speed;
                }
                if (dir == 1 && coordinates.x < 400) {
                    coordinates.x += dir * speed;
                    Xshift = 0;
                }

                if(Xshift>1000)
                {
                    Xshift=1000;
                    coordinates.x += dir * speed;
                }
                if(dir==-1 && coordinates.x>400)
                {
                    coordinates.x += dir * speed;
                    Xshift = 1000;
                }

                break;
            case JUMPING:
                if (!isgrounded) {
                    anim_tickY = 3;
                    for (int i = 2; i < 5; i++) {
                        if (animation_speed >= 40) {
                            animation_speed = 0;
                            if (anim_tickX >= 4)
                                anim_tickX = 0;
                            anim_tickX += 1;
                        }
                        animation_speed += 1;
                    }
                }
                coordinates.y -= 15;
                break;
            case ATTACKING:
                anim_tickY = 1;
                for (int i = 0; i < 5; i++) {
                    if (animation_speed >= 40) {
                        animation_speed = 0;
                        if (anim_tickX >= 4)
                            anim_tickX = 0;
                        anim_tickX += 1;

                    }
                    wasAttacked=false;
                    animation_speed += 1;
                }
                break;

        }
        isgrounded = false;
        wasAttacked = false;
    }
public void craft()
{
    if(inventory.mushroomCount>=1 && inventory.waterCount>=1)
    {
        inventory.healthPotionCount+=1;
        inventory.mushroomCount-=1;
        inventory.waterCount-=1;
    }
}
    public void usePowerPotion()
    {
        if(inventory.healthPotionCount>=1)
        {
            attack+=1;
            inventory.healthPotionCount--;
        }
}

    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Initiates an attack action against an enemy.
     *
     * @param enemy the enemy to attack
     */
    public void attack(Enemy enemy) {
        if(enemy.current_health>0 && state==MovementState.ATTACKING) {

            if (!wasAttacked && enemy.current_health > 0 && enemy.hitbox.intersects(hitbox)) {
                enemy.current_health -= attack;
                wasAttacked = true;
            }

        }

    }

    public int getXshift() {
        return Xshift;
    }
}
