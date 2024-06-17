package org.example.view;

import javax.swing.*;

/**
 * Represents the main game loop responsible for updating and rendering the game world.
 */
public class GameLoop implements Runnable {

    /** The game world instance. */
   public GameWorld gameWorld;
    /** The main frame of the game. */
   public MainFrame mainFrame;
    /** The thread running the game loop. */
   public Thread thread;
    /** The target frames per second (FPS) for the game loop. */
    private final int FPS = 60;

    /**
     * Constructs a new GameLoop object.
     */
    public GameLoop() {
        gameWorld = new GameWorld();
        mainFrame = new MainFrame("Game", gameWorld);
        gameWorld.requestFocus();
        start();
    }

    /**
     * Starts the game loop thread.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * The main loop of the game responsible for updating and rendering the game world.
     */
    @Override
    public void run() {
        double framePerSecond = 1000000000.0 / FPS;
        int frames = 0;
        long now = System.nanoTime();
        long lastFrame = now;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();

            if (now - lastFrame >= framePerSecond) {
                if(!gameWorld.hunter.isAlive) {
                    JOptionPane.showMessageDialog(gameWorld, "Game Over");
                    System.exit(0);
                }
                if(gameWorld.hunter.coordinates.x>850&& gameWorld.hunter.inventory.gold_count>=3){
                    JOptionPane.showMessageDialog(gameWorld, "You Win");
                    System.exit(0);
                }
                if(gameWorld.hunter.coordinates.x>850&& gameWorld.hunter.inventory.gold_count<3){
                    JOptionPane.showMessageDialog(gameWorld, "You need 3 gold to pass");
                    gameWorld.hunter.coordinates.x=830;
                }
                gameWorld.repaint();
                lastFrame = now;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                System.out.println("FPS: " + frames);
                lastCheck = System.currentTimeMillis();
                frames = 0;
            }
        }
    }
}
