package org.example.view;

/**
 * Enum representing file paths for various resources used in the game.
 */
public enum FilePath {
    /** File path for the sprite sheet of the player character (knight). */
    HUNTER("src/res/knight-sprite-sheet.png"),
    /** File path for the image of a coin. */
    COIN("src/res/Coin.png"),
    /** File path for the image of a monster. */
    MONSTER("src/res/monster.png"),
    /** File path for the image of the ground in the castle. */
    CASTLE_GROUND("src/res/Sprite/Ground.png"),
    /** File path for the image of the background in the castle. */
    BACKGROUND("src/res/castle/background.png"),
    /** File path for the image of a door. */
    DOOR("src/res/Sprite/Door.png"),
    /** File path for the image of a pillar. */
    PILLAR("src/res/Sprite/Pillar.png"),
    /** File path for the image of a wall. */
    WALL("src/res/Sprite/Wall.png"),
    /** File path for the image of a key. */
    KEY("src/res/key.png"),
    /** File path for the image of the inventory. */
    INVENTORY("src/res/Inventory.png"),
    /** File path for the image of a non-player character (NPC). */
    NPS("src/res/nps.png"),
    MUSHROOM("src/res/Mush.png"),
    HEALTH_POTION("src/res/power.png"),
    WATER("src/res/water.png");


    private final String path;

    /**
     * Constructs a new FilePath enum with the specified file path.
     *
     * @param path the file path
     */
    FilePath(String path) {
        this.path = path;
    }

    /**
     * Returns the file path.
     *
     * @return the file path
     */
    public String getPath() {
        return path;
    }
}
