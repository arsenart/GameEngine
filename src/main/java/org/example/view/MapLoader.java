package org.example.view;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.model.*;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for loading game map data and entities from JSON files.
 */
public class MapLoader {

    /**
     * Loads the map blocks from the specified file path.
     *
     * @param filePath the file path to load the map blocks from
     * @return a list of blocks representing the game map
     */
    public static List<Block> loadMap(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Block[] blocks = gson.fromJson(jsonObject.getAsJsonArray("blocks"), Block[].class);
            for (Block block : blocks) {
                block.hitbox = new Rectangle(block.x, block.y, block.width, block.height);
            }
            return List.of(blocks);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Loot> LoadLoot(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Loot[] loots = gson.fromJson(jsonObject.getAsJsonArray("loot"), Loot[].class);
            for (Loot loot : loots) {
                loot.hitbox = new Rectangle(loot.coordinates.x, loot.coordinates.y, 32, 32);
            }
            return List.of(loots);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Loads the enemies from the specified file path.
     *
     * @param filePath the file path to load the enemies from
     * @return a list of enemies in the game
     */
    public static List<Enemy> LoadEnemy(String filePath) {
        List<Enemy> enemiesCopy = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Enemy[] enemies = gson.fromJson(jsonObject.getAsJsonArray("enemies"), Enemy[].class);
            for (Enemy enemy : enemies) {
                if (enemy.isAlive) {
                    enemiesCopy.add(enemy);
                }
            }
            return enemiesCopy;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Loads the NPC from the specified file path.
     *
     * @param filePath the file path to load the NPC from
     * @return the NPC object
     */
    public static NPC LoadNPS(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            NPC npc = gson.fromJson(jsonObject.getAsJsonObject("npc"), NPC.class);
            npc.hitbox = new Rectangle(npc.coordinates.x, npc.coordinates.y, 90, 70);
            return npc;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Loads the hunter (player character) from the specified file path.
     *
     * @param filePath the file path to load the hunter from
     * @return the hunter object
     */
    public static Hunter LoadHunter(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Hunter hunter = gson.fromJson(jsonObject.getAsJsonObject("hunter"), Hunter.class);
            hunter.state = MovementState.IDLE;
            hunter.dir = 1;
            hunter.revers = -90;
            hunter.hitbox = new Rectangle(hunter.coordinates.x + hunter.Xshift, hunter.coordinates.y, 90, 70);
            return hunter;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Loads the gold loot from the specified file path.
     *
     * @param filePath the file path to load the gold loot from
     * @return a list of gold loot objects
     */
    public static List<Gold> LoadGold(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Gold[] gold = gson.fromJson(jsonObject.getAsJsonArray("golds"), Gold[].class);
            for (Gold gold1 : gold) {
                gold1.hitbox = new Rectangle(gold1.coordinates.x, gold1.coordinates.y, 32, 32);
            }
            return List.of(gold);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
