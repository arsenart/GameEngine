package org.example.view;

import org.example.model.*;

import java.util.List;
import java.awt.*;

/**
 * Manages collision detection between game elements.
 */
public class Collision {

    /**
     * Checks collision between the hunter, blocks, enemies, gold, and NPC.
     *
     * @param hunter  the player character
     * @param blocks  the list of blocks in the game world
     * @param enemies the list of enemies in the game world
     * @param golds   the list of gold loot in the game world
     * @param npc     the non-player character in the game world
     */
    public void checkCollision(Hunter hunter, List<Block> blocks, List<Enemy> enemies, List<Gold> golds, NPC npc, List<Loot> loots) {
        for (Block block : blocks) {
            if (block.type.equals("background"))
                continue;
            if (hunter.hitbox.intersects(block.hitbox)) {
                hunter.isgrounded = true;
            }
            for (Enemy enemy : enemies) {
                if (enemy.hitbox.intersects(block.hitbox)) {
                    enemy.isGrounded = true;
                }
            }
        }
        for (Enemy enemy : enemies) {
            if (hunter.hitbox.intersects(enemy.hitbox)) {
                hunter.collision = true;
//                enemy.coordinates.x+=hunter.speed* hunter.dir;
                hunter.attack(enemy);
            }

        }

        for (Gold gold : golds) {
            if (!gold.isVisible)
                continue;
            if (hunter.hitbox.intersects(gold.hitbox)) {
                gold.isVisible = false;
                hunter.inventory.gold_count += 1;
            }
        }
        for (Loot loot : loots) {
            if (!loot.isVisible)
                continue;
            if (hunter.hitbox.intersects(loot.hitbox)) {

                if(loot.name.equals("mushroom"))
                {
                    hunter.inventory.mushroomCount+=1;
                    loot.isVisible = false;
                }
                if(loot.name.equals("water"))
                {
                    hunter.inventory.waterCount+=1;
                    loot.isVisible = false;
                }
            }
        }
    }

    /**
     * Checks collision between the hunter and the NPC.
     *
     * @param hunter the player character
     * @param npc    the non-player character in the game world
     */
    private void checkNpsCollision(Hunter hunter, NPC npc) {
        if (hunter.hitbox.intersects(npc.hitbox)) {
            // Implement actions for collision between hunter and NPC
        }
    }
}
