package org.example;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.gson.JsonObject;
import org.example.model.*;
import org.example.view.GameWorld;
import org.example.view.MapLoader;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import org.mockito.*;

public class GameWorldTest {
    private GameWorld gameWorld;

    @Before
    public void setup() {
        gameWorld = new GameWorld();
    }

    @Test
    public void testGameLoad() {
        gameWorld.gameLoad("src/main/java/org/example/view/lastSave.json");
        assertNotNull("Hunter should not be null", gameWorld.hunter);
        assertNotNull("NPC should not be null", gameWorld.npc);
        assertFalse("Golds list should not be empty", gameWorld.golds.isEmpty());
        assertFalse("Blocks list should not be empty", gameWorld.blocks.isEmpty());
        assertFalse("Enemies list should not be empty", gameWorld.enemies.isEmpty());
        assertFalse("Loots list should not be empty", gameWorld.loots.isEmpty());
    }



}
