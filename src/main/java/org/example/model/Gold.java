package org.example.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents a gold loot object in the game.
 */
public class Gold extends Loot {

    /**
     * Constructs a new Gold object with the specified value, coordinates, and visibility status.
     *
     * @param value       the value of the gold loot
     * @param coordinates the coordinates of the gold loot
     * @param isVisible   the visibility status of the gold loot
     */
    public Gold(int value, Coordinates coordinates, boolean isVisible) {
        this.value = value;
        this.coordinates = coordinates;
        this.isVisible = isVisible;
    }

    /**
     * Updates the position and behavior of the gold loot based on the surrounding blocks.
     *
     * @param blocks the list of blocks in the game world
     */
    public void update(List<Block> blocks) {
        hitbox = new Rectangle(coordinates.x + 20, coordinates.y, 25, 64);

        for (Block block : blocks) {
            if (hitbox.intersects(block.hitbox)) {
                isGrounded = true;
            }
        }
        if (!isGrounded) {
            coordinates.y += Math.min(5, 700 - coordinates.y);
        }

        for (int i = 0; i < 12; i++) {
            if (animation_speed > 50) {
                animation_speed = 0;
                if (anim_tickX >= 11)
                    anim_tickX = 0;
                anim_tickX += 1;
            }
            animation_speed += 1;
        }
        isGrounded = false;
        collision = false;
    }
}
