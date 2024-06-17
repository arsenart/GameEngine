package org.example.model;

import com.google.gson.annotations.Expose;
import org.example.view.MovementState;

import java.awt.*;

/**
 * Represents a unit in the game world, such as characters or enemies.
 */
public class Unit {

    /** The type of the unit. */
    String type;
    /** The current movement state of the unit (not serialized). */
    public transient MovementState state;
    /** The direction of the unit's movement. */
    public transient int dir;
    /** The reverse direction of the unit's movement. */
    public transient int revers;
    /** The health points of the unit. */
    public int health;
    /** The current health points of the unit. */
    public int current_health;
    /** The attack power of the unit. */
    public int attack;
    /** The movement speed of the unit. */
    public int speed;
    /** The coordinates of the unit. */
    public Coordinates coordinates;
    /** The X-axis animation tick of the unit (not serialized). */
    public transient int anim_tickX;
    /** The Y-axis animation tick of the unit (not serialized). */
    public transient int anim_tickY;
    /** The animation speed of the unit (not serialized). */
    protected transient int animation_speed;
    /** Flag indicating if the unit is alive. */
    public boolean isAlive = true;
    /** Flag indicating if the unit has collided with something (not serialized). */
    public transient boolean collision = false;

    public int getAnim_tickX() {
        return anim_tickX;
    }

    public int getAnim_tickY() {
        return anim_tickY;
    }

    public int getAnimation_speed() {
        return animation_speed;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isCollision() {
        return collision;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getDir() {
        return dir;
    }

    public int getCurrent_health() {
        return current_health;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getRevers() {
        return revers;
    }

    public int getSpeed() {
        return speed;
    }

    public MovementState getState() {
        return state;
    }

    public String getType() {
        return type;
    }
}
