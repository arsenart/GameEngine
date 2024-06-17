package org.example;

import org.example.model.Coordinates;
import org.example.model.Enemy;
import org.example.model.Hunter;
import org.example.model.Inventory;
import org.example.view.MovementState;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ProcessTest {
    @Test
    public void testHunterInitialization() {
        Inventory inventory = new Inventory(1,1,1,1);
        Coordinates coordinates = new Coordinates(100, 100);
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, coordinates, inventory);

        assertEquals(100, hunter.health);
        assertEquals(100, hunter.current_health);
        assertEquals(10, hunter.attack);
        assertEquals(5, hunter.speed);
        assertNotNull(hunter.hitbox);
        assertEquals(50, hunter.hitbox.width);
        assertEquals(70, hunter.hitbox.height);
        assertEquals(0, hunter.getXshift());
    }
    @Test
    public void testHunterMovement() {
        Inventory inventory = new Inventory(1,1,1,1);
        Coordinates coordinates = new Coordinates(60, 100);
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, coordinates, inventory);
        hunter.state = MovementState.MOVING;
        hunter.dir = 1;
        hunter.move();

        assertEquals(65, hunter.coordinates.x);
        assertTrue(hunter.getXshift() >= 0 && hunter.getXshift() <= 1000);
    }
    @Test
    public void testHunterJumping() {
        Inventory inventory = new Inventory(1,1,1,1);
        Coordinates coordinates = new Coordinates(100, 685);
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, coordinates, inventory);
        hunter.state = MovementState.JUMPING;
        hunter.isgrounded = false;
        hunter.move();

        assertTrue(hunter.coordinates.y < 685);
    }
    @Test
    public void testCrafting() {
        Inventory inventory = new Inventory(1,0,1,1);
        inventory.mushroomCount = 1;
        inventory.waterCount = 1;
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, new Coordinates(100, 100), inventory);
        hunter.craft();

        assertEquals(1, inventory.healthPotionCount);
        assertEquals(0, inventory.mushroomCount);
        assertEquals(0, inventory.waterCount);
    }
    @Test
    public void testAttacking() {
        Inventory inventory = new Inventory(1,1,1,1);
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, new Coordinates(100, 100), inventory);
        Enemy enemy = new Enemy("enemy",10,10,5,5,new Coordinates(120,100),true);
        enemy.current_health = 50;
        enemy.hitbox = new Rectangle(100, 100, 50, 70);
        hunter.state = MovementState.ATTACKING;

        hunter.attack(enemy);
        assertEquals(40, enemy.current_health);
    }
    @Test
    public void testUseHealthPotion() {
        Inventory inventory = new Inventory(1,1,1,1);
        inventory.healthPotionCount = 1;
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, new Coordinates(100, 100), inventory);
        hunter.usePowerPotion();

        assertEquals(11, hunter.attack);
        assertEquals(0, inventory.healthPotionCount);
    }
    @Test
    public void testGravityEffect() {
        Inventory inventory = new Inventory(1,1,1,1);
        Coordinates coordinates = new Coordinates(100, 50);
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, coordinates, inventory);
        hunter.isgrounded = false;
        hunter.move();

        assertTrue(coordinates.y > 50);
    }
    @Test
    public void testHitboxUpdateOnMovement() {
        Inventory inventory = new Inventory(1,1,1,1);
        Coordinates coordinates = new Coordinates(100, 100);
        Hunter hunter = new Hunter(0, 100, 100, 10, 5, coordinates, inventory);
        hunter.move();
        Rectangle expectedHitbox = new Rectangle(coordinates.x - 60 + hunter.Xshift, coordinates.y-5, 30, 70);

        assertEquals(expectedHitbox, hunter.getHitbox());
    }
}
