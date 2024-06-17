package org.example.model;

import com.google.gson.annotations.Expose;

import java.awt.*;

/**
 * Class representing a block in the game world.
 */
public class Block {
    /** The type of the block. */
    public String type;
    /** The position of the block on the X-axis. */
    public int x;
    /** The position of the block on the Y-axis. */
    public int y;
    /** The width of the block. */
    public int width;
    /** The height of the block. */
    public int height;
    /** The rectangle for collision detection (not serialized). */
    transient public Rectangle hitbox;

    /**
     * Creates a new instance of a block.
     *
     * @param type    the type of the block
     * @param x       the position on the X-axis
     * @param y       the position on the Y-axis
     * @param width   the width of the block
     * @param height  the height of the block
     */
    public Block(String type, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the type of the block.
     *
     * @return the type of the block
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the block.
     *
     * @param type the type of the block
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the position on the X-axis.
     *
     * @return the position on the X-axis
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the position on the X-axis.
     *
     * @param x the position on the X-axis
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the position on the Y-axis.
     *
     * @return the position on the Y-axis
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the position on the Y-axis.
     *
     * @param y the position on the Y-axis
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the width of the block.
     *
     * @return the width of the block
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the block.
     *
     * @param width the width of the block
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the block.
     *
     * @return the height of the block
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the block.
     *
     * @param height the height of the block
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
