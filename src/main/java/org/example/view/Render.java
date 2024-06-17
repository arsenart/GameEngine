package org.example.view;

import org.example.model.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Render {
    public Map<String, BufferedImage> sprites = new HashMap<>();
    BufferedImage[][] enemySprites;
    BufferedImage[][] hunterSprites;
    BufferedImage[]npsSprites;
    BufferedImage[]goldSprites;
    Hunter hunter;
    public Render(Hunter hunter) {
        this.hunter =hunter;
        initializeWorldSprites();
        initializeUnitSprites();

    }
    private void initializeWorldSprites() {
        sprites.put("ground", new Sprite(FilePath.CASTLE_GROUND.getPath()).image);
        sprites.put("background", new Sprite(FilePath.BACKGROUND.getPath()).image);
        sprites.put("door", new Sprite(FilePath.DOOR.getPath()).image);
        sprites.put("pillar", new Sprite(FilePath.PILLAR.getPath()).image);
        sprites.put("wall", new Sprite(FilePath.WALL.getPath()).image);
        sprites.put("key", new Sprite(FilePath.KEY.getPath()).image);
        sprites.put("inventory", new Sprite(FilePath.INVENTORY.getPath()).image);
        sprites.put("coin", new Sprite(FilePath.COIN.getPath()).image);
        sprites.put("knight", new Sprite(FilePath.HUNTER.getPath()).image);
        sprites.put("enemy", new Sprite(FilePath.MONSTER.getPath()).image);
        sprites.put("nps", new Sprite(FilePath.NPS.getPath()).image);
        sprites.put("mushroom", new Sprite(FilePath.MUSHROOM.getPath()).image);
        sprites.put("power", new Sprite(FilePath.HEALTH_POTION.getPath()).image);
        sprites.put("water", new Sprite(FilePath.WATER.getPath()).image);
    }
    /**
     * Draws the health bar for a character.
     *
     * @param g            the Graphics object for rendering
     * @param x            the x-coordinate of the health bar
     * @param y            the y-coordinate of the health bar
     * @param currentHealth the current health of the character
     * @param maxHealth     the maximum health of the character
     */
    private void drawHealthBar(Graphics g, int x, int y, int currentHealth, int maxHealth) {
        final int BAR_WIDTH = 100;
        final int BAR_HEIGHT = 5;
        int healthWidth = (int) ((currentHealth / (double) maxHealth) * BAR_WIDTH);
        g.setColor(Color.GREEN);
        g.fillRect(x, y - 10, healthWidth, BAR_HEIGHT);
        g.setColor(Color.RED);
        g.fillRect(x + healthWidth, y - 10, BAR_WIDTH - healthWidth, BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y - 10, BAR_WIDTH, BAR_HEIGHT);
    }


    /**
     * Initializes the sprite images for game units (hunter, enemy, NPC, gold).
     */
    public void initializeUnitSprites() {
        hunterSprites = new BufferedImage[4][5];
        for (int i = 0; i < 5; i++) {
            hunterSprites[0][i] = sprites.get("knight").getSubimage(90 * i, 0, 90, 100);
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                hunterSprites[i][j] = sprites.get("knight").getSubimage(90 * j, 80 * i, 90, 100);
            }
        }
        enemySprites = new BufferedImage[2][8];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                enemySprites[i][j] = sprites.get("enemy").getSubimage(64 * j, 64 * i, 64, 64);
            }

        }
        npsSprites = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            npsSprites[i] = sprites.get("nps").getSubimage(70*i, 0, 70, 100);
        }
        goldSprites=new BufferedImage[12];
        for (int i = 0; i < 12; i++) {
            goldSprites[i] = sprites.get("coin").getSubimage(32*i, 0, 32, 32);
        }
    }

    /**
     * Renders an enemy object.
     *
     * @param g     the Graphics object for rendering
     * @param enemy the enemy object to render
     */
    public void EnemyRender(Graphics g, Enemy enemy) {

        if(enemy.current_health>0) {
            g.drawImage(enemySprites[enemy.anim_tickY][enemy.anim_tickX], enemy.coordinates.x - hunter.Xshift, enemy.coordinates.y, 64, 64, null);
            // g.drawRect(enemy.hitbox.x-hunter.Xshift, enemy.hitbox.y, enemy.hitbox.width, enemy.hitbox.height);
            drawHealthBar(g, enemy.coordinates.x - hunter.Xshift, enemy.coordinates.y, enemy.current_health, enemy.health);
        }
        else
            g.drawImage(sprites.get("enemy").getSubimage(64*4, 64*4, 64, 64), enemy.coordinates.x - hunter.Xshift, enemy.coordinates.y, 64, 64, null);

    }
    /**
     * Renders the hunter object.
     *
     * @param g      the Graphics object for rendering
     * @param hunter the hunter object to render
     */
    public void HunterRender(Graphics g, Hunter hunter) {
        g.drawImage(hunterSprites[hunter.anim_tickY][hunter.anim_tickX], hunter.coordinates.x + hunter.revers, hunter.coordinates.y, 90 * hunter.dir, 100, null);
        InventoryRender(g,hunter.inventory);
//        g.drawRect(hunter.hitbox.x-hunter.Xshift, hunter.hitbox.y, hunter.hitbox.width, hunter.hitbox.height);
        drawHealthBar(g, hunter.coordinates.x-50 , hunter.coordinates.y, hunter.current_health, hunter.health);
    }
    /**
     * Renders a gold object.
     *
     * @param g    the Graphics object for rendering
     * @param gold the gold object to render
     */
    public    void GoldRender(Graphics g, Gold gold){
        if(gold.isVisible==true) {
            g.drawImage(goldSprites[gold.anim_tickX], gold.coordinates.x - hunter.Xshift, gold.coordinates.y, 40, 40, null);
         //   g.drawRect(gold.hitbox.x - hunter.Xshift, gold.hitbox.y, gold.hitbox.width, gold.hitbox.height);
        }
    }
    private void InventoryRender(Graphics g, Inventory inventory) {
        g.drawImage(sprites.get("inventory"),0, 0, 50,100, null);
        if(inventory.waterCount>1&& inventory.mushroomCount>1)
        {
            g.drawString("Press q to create power potion", 100, 50);
        }
        if(inventory.healthPotionCount>0)
        {
            g.drawImage(sprites.get("power"),15, 45, 20,20, null);
            g.drawString("P"+inventory.healthPotionCount, 50, 55);
            g.drawString("Press p to use power potion", 100, 50);
        }
        if(inventory.waterCount>0)
        {
            g.drawImage(sprites.get("water"),15, 30, 20,20, null);
            g.drawString("W"+inventory.waterCount, 50, 43);
        }
        if(inventory.gold_count>0)
        {
           g.drawImage(sprites.get("coin").getSubimage(32*6,0,32,32),10, 10, 32,26, null);
            g.drawString("C"+inventory.gold_count, 50, 30);
        }

        if(inventory.mushroomCount>0)
        {
            g.drawImage(sprites.get("mushroom"),15, 0, 20,20, null);
            g.drawString("M"+inventory.mushroomCount, 50, 13);

        }
    }

    /**
     * Renders an NPC object.
     *
     * @param g   the Graphics object for rendering
     * @param nps the NPC object to render
     */
    public void NpsRender(Graphics g, NPC nps)
    {
    g.drawImage(npsSprites[nps.anim_tickX],nps.coordinates.x-hunter.Xshift,nps.coordinates.y,60,70,null);
    }
    /**
     * Draws the blocks of the game world.
     *
     * @param g      the Graphics object for rendering
     * @param blocks the list of blocks representing the game world
     */
    public void drawBlocks(Graphics g, List<Block> blocks) {
        for (Block block : blocks) {
            BufferedImage image = sprites.get(block.getType());
            if (image != null) {
                g.drawImage(image, block.x - hunter.Xshift, block.y, block.width, block.height, null);
                block.hitbox.x = block.x;
                //g.drawRect(block.hitbox.x-hunter.Xshift, block.hitbox.y, block.hitbox.width, block.hitbox.height);
            }
        }
    }
    public void drawLoot(Graphics g, List<Loot> loots) {
        for (Loot loot : loots) {
            if (loot.isVisible) {
                    loot.hitbox=new Rectangle(loot.coordinates.x, loot.coordinates.y, 40, 40);
                    g.drawImage(sprites.get(loot.name), loot.coordinates.x - hunter.Xshift, loot.coordinates.y, 40, 40, null);
                   // g.drawRect(loot.hitbox.x-hunter.Xshift, loot.hitbox.y, loot.hitbox.width, loot.hitbox.height);

            }
        }
    }
}
