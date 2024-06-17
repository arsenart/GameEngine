package org.example.model;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Represents a sprite image in the game.
 */
public class Sprite {

    /** The image of the sprite. */
    public BufferedImage image;
    /** The file containing the sprite image. */
    public File file;

    /**
     * Constructs a new Sprite object with the image loaded from the specified file path.
     *
     * @param path the path to the sprite image file
     */
    public Sprite(String path) {
        try {
            this.file = new File(path);
            this.image = javax.imageio.ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
