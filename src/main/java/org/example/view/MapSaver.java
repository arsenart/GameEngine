package org.example.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Utility class for saving game map data and entities to JSON files.
 */
public class MapSaver {

    /** The hunter object to be saved. */
    public Hunter hunter;
    /** The list of blocks representing the game map. */
    public List<Block> blocks;
    /** The list of enemies in the game. */
    public List<Enemy> enemies;
    /** The list of gold loot in the game. */
    public List<Gold> golds;
    /** The NPC object to be saved. */
    public NPC npc;
    public List<Loot> loot;

    /**
     * Constructs a new MapSaver object with the specified game entities.
     *
     * @param hunter  the hunter object to be saved
     * @param blocks  the list of blocks representing the game map
     * @param enemies the list of enemies in the game
     * @param golds   the list of gold loot in the game
     * @param npc     the NPC object to be saved
     */
    public MapSaver(Hunter hunter, List<Block> blocks, List<Enemy> enemies, List<Gold> golds, NPC npc, List<Loot> loot) {
        this.hunter = hunter;
        this.blocks = blocks;
        this.enemies = enemies;
        this.golds = golds;
        this.npc = npc;
        this.loot=loot;
    }

    /**
     * Saves the game map data and entities to the specified file path.
     *
     * @param filePath the file path to save the game map data and entities
     */
    public void saveMap(String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
