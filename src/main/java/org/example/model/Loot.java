package org.example.model;

import org.example.view.Render;

import java.awt.*;

/**
 * Represents a loot object in the game.
 */
public class Loot {

    /** The value of the loot. */
    public int value;
    /** The coordinates of the loot. */
    public Coordinates coordinates;
    /** The name of the loot (not serialized). */
    public  String name;
    /** The animation speed of the loot (not serialized). */
    public transient int animation_speed;
    /** The X-axis animation tick of the loot (not serialized). */
    public transient int anim_tickX;
    /** The hitbox of the loot (not serialized). */
    public transient Rectangle hitbox;
    /** Flag indicating if the loot has collided with something (not serialized). */
    public transient boolean collision = false;
    /** Flag indicating if the loot is visible. */
    public boolean isVisible = true;
    /** Flag indicating if the loot is grounded (not serialized). */
    public transient boolean isGrounded = false;

    public Loot(String name,Coordinates coordinates,boolean isVisible){
        this.coordinates=coordinates;
        this.name=name;
        this.isVisible=isVisible;
    }
    public Loot()
    {

    }
    /**
     * Performs an action when the loot is used.
     */
    public void use() {
        // Implementation of the use action goes here
    }

    /**
     * Animates the loot object.
     *
     * @param g the graphics context used for drawing
     */
    public void animate(Graphics g) {
        // Implementation of the animation goes here
    }

}
