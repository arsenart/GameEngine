package org.example;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.example.model.*;
import org.example.view.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;


import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Test
  public   void testGameLoad() {
        String filePath = "src/main/java/org/example/view/lastSave.json";


        try (MockedStatic<MapLoader> mockedMapLoader = Mockito.mockStatic(MapLoader.class)) {
            GameWorld gameWorld = new GameWorld();
            gameWorld.gameLoad(filePath);


            mockedMapLoader.verify(() -> MapLoader.LoadEnemy(filePath), Mockito.times(2));
            mockedMapLoader.verify(() -> MapLoader.loadMap(filePath), Mockito.times(2));
            mockedMapLoader.verify(() -> MapLoader.LoadGold(filePath), Mockito.times(2));
            mockedMapLoader.verify(() -> MapLoader.LoadHunter(filePath), Mockito.times(2));
            mockedMapLoader.verify(() -> MapLoader.LoadNPS(filePath), Mockito.times(2));
            mockedMapLoader.verify(() -> MapLoader.LoadLoot(filePath), Mockito.times(2));
        }
    }
}