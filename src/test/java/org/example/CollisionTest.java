package org.example;

import org.example.model.*;
import org.example.view.Collision;
import org.example.view.MovementState;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollisionTest {
    Inventory inventory = new Inventory(1,1,1,1);
    Hunter hunter = new Hunter(0, 100, 100, 10, 5, new Coordinates(0, 515), inventory);

    List<Block> blocks= List.of(new Block("ground",0,515, 32, 32), new Block("ground",32,515, 32, 32));
    Collision collision=new Collision();

    @Test
    public void testCheckCollision() {
        // Ensure hitbox is initialized
        if (hunter.hitbox == null) {
            hunter.hitbox = new Rectangle(hunter.coordinates.x, hunter.coordinates.y, 90, 70);
        }

        for (Block block : blocks) {
            if (block.hitbox == null) {
                block.hitbox = new Rectangle(block.x, block.y, block.width, block.height);
            }
        }

        collision.checkCollision(hunter, blocks, new ArrayList<>(), new ArrayList<>(), new NPC("gg", new Coordinates(400, 515)), new ArrayList<>());
        assertTrue(hunter.isgrounded);
    }
    @Test
    public void testHunterMovementLeftBoundary() {
        // Setup
        Inventory inventory = new Inventory(1,1,1,1);
        Hunter hunter = new Hunter(0, 100, 100, 10, 10, new Coordinates(60, 0), inventory);
        hunter.move();

        // Assert that Hunter's x-coordinate does not go below 60 after moving left
        assertEquals(60, hunter.coordinates.x);
    }
    @Test
    public void testHunterMovementRightBoundary() {
        // Setup
        Inventory inventory = new Inventory(1,1,1,1);
        Hunter hunter = new Hunter(0, 100, 100, 10, 10, new Coordinates(740, 0), inventory);
        hunter.move();

        // Assert that Hunter's x-coordinate does not go above 740 after moving right
        assertEquals(740, hunter.coordinates.x);
    }
    @Test
    public void testSuccessfulPotionCrafting() {
        // Setup
        Inventory inventory = new Inventory(1,0,1,1);
        Hunter hunter = new Hunter(0, 100, 100, 10, 10, new Coordinates(100, 100), inventory);

        // Action
        hunter.craft();

        // Assert that a health potion is crafted and resources are decremented
        assertEquals(1, inventory.healthPotionCount);
        assertEquals(0, inventory.mushroomCount);
        assertEquals(0, inventory.waterCount);
    }
    @Test
    public void testUsePowerPotionIncreasesAttack() {
        // Setup
        Inventory inventory = new Inventory(1,1,1,1);
        inventory.healthPotionCount = 1;
        Hunter hunter = new Hunter(0, 100, 100, 10, 10, new Coordinates(100, 100), inventory);
        int initialAttack = hunter.attack;

        // Action
        hunter.usePowerPotion();

        assertEquals(initialAttack + 1, hunter.attack);
        assertEquals(0, inventory.healthPotionCount);
    }
    @Test
    public void testHunterAttackReducesEnemyHealth() {
        // Setup
        Inventory inventory = new Inventory(1,1,1,1);
        Hunter hunter = new Hunter(0, 100, 100, 10, 10, new Coordinates(100, 100), inventory);
        hunter.state = MovementState.ATTACKING;
        Enemy enemy = new Enemy("type", 20, 20, 10, 5, new Coordinates(100, 100), true);
        enemy.current_health = 20;

        // Action
        hunter.attack(enemy);

        // Assert enemy health is reduced
        assertEquals(10, enemy.current_health);  // Assuming Hunter's attack power is 10
        assertTrue(hunter.wasAttacked);
    }
}
