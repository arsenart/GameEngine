package org.example.view;

import org.example.model.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.*;

/**
 * Represents the game world where all game elements are rendered and updated.
 */
public class GameWorld extends JPanel {
    /** The player character. */
    public Hunter hunter;
    /** The non-player character (NPC). */
    public NPC npc;
    /** The list of gold loot in the game world. */
    public List<Gold> golds;
    /** The list of blocks in the game world. */
    public List<Block> blocks;
    public List<Loot> loots;
    /** The list of enemies in the game world. */
    public List<Enemy> enemies;
    /** The renderer for the game world. */

    public Render render;

    /** The collision manager for the game world. */
    public Collision collision = new Collision();


    /**
     * Constructs a new GameWorld object.
     */
    public GameWorld() {
        addKeyListener(new KeyBoard(this));
        setPreferredSize(new Dimension(800, 600));
        gameLoad("src/main/java/org/example/view/level.json");

        render = new Render(hunter);
    }

    /**
     * Loads game elements from the specified file path.
     *
     * @param filePath the file path to load game elements from
     */
    public void gameLoad(String filePath) {
        blocks = MapLoader.loadMap(filePath);
        enemies = MapLoader.LoadEnemy(filePath);
        golds = MapLoader.LoadGold(filePath);
        hunter = MapLoader.LoadHunter(filePath);
        npc = MapLoader.LoadNPS(filePath);
        loots = MapLoader.LoadLoot(filePath);

    }

    /**
     * Draws the enemy units and updates their movements.
     *
     * @param g       the graphics context to draw on
     * @param enemies the list of enemies to draw and update
     */
    private void drawUnits(Graphics g, List<Enemy> enemies) {

        for (Enemy enemy : enemies) {
            if(enemy.isAlive) {
                enemy.move(hunter);
                render.EnemyRender(g, enemy);
            }

        }
    }

    /**
     * Draws the gold loot and updates their positions.
     *
     * @param g     the graphics context to draw on
     * @param golds the list of gold loot to draw and update
     */
    private void drawGold(Graphics g, List<Gold> golds) {
        for (Gold gold : golds) {
            render.GoldRender(g, gold);
            gold.update(blocks);
        }
    }
    private void  gameOver(){

    }

    /**
     * Renders and updates the game world.
     *
     * @param g the graphics context to render on
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render.drawBlocks(g, blocks);
        render.drawLoot(g, loots);
        drawUnits(g, enemies);
        drawGold(g, golds);
        collision.checkCollision(hunter, blocks, enemies, golds, npc,loots);
        hunter.move();
        render.NpsRender(g, npc);
        npc.update();
        render.HunterRender(g, hunter);
        System.out.println(hunter.coordinates.x);
        gameOver();

    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public List<Gold> getGolds() {
        return golds;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Collision getCollision() {
        return collision;
    }

    public List<Loot> getLoots() {
        return loots;
    }

    public NPC getNpc() {
        return npc;
    }

    public Render getRender() {
        return render;
    }
}
