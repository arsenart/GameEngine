package org.example.model;

import org.example.view.DialogWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a non-player character (NPC) in the game.
 */
public class NPC extends Unit {

    /** The name of the NPC. */
    public String name;
    /** The list of items the NPC has for sale (not serialized). */
    transient public List<Loot> ForSale;
    /** The hitbox of the NPC (not serialized). */
    transient public Rectangle hitbox;

    /**
     * Constructs a new NPC object with the specified name and coordinates.
     *
     * @param name        the name of the NPC
     * @param coordinates the initial coordinates of the NPC
     */
    public NPC(String name, Coordinates coordinates) {
        this.name = name;
        this.ForSale = new ArrayList<>();
        this.coordinates = new Coordinates(515, 515);
    }

    /**
     * Updates the state of the NPC.
     */
    public void update() {
        for (int i = 0; i < 3; i++) {
            if (animation_speed >= 40) {
                animation_speed = 0;
                if (anim_tickX >= 2)
                    anim_tickX = 0;
                anim_tickX += 1;
            }
        }
        animation_speed += 1;
    }
}
