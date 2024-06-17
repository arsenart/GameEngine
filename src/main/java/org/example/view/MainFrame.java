package org.example.view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main frame of the game.
 */
public class MainFrame {

    /** The JFrame instance representing the main frame. */
    private final JFrame frame;
    /** The title of the main frame. */
    public String title;
    /** The GameWorld instance associated with the main frame. */
    public GameWorld gameWorld;

    /**
     * Constructs a new MainFrame object with the specified title and GameWorld.
     *
     * @param title     the title of the main frame
     * @param gameWorld the GameWorld associated with the main frame
     */
    public MainFrame(String title, GameWorld gameWorld) {
        this.title = title;
        this.frame = new JFrame(title);
        this.gameWorld = gameWorld;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameWorld);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
