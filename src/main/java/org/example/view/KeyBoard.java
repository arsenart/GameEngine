package org.example.view;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles keyboard input for controlling the game.
 */
public class KeyBoard implements KeyListener {

    /** The game world associated with the keyboard input. */
    GameWorld gameWorld;
    /** The map saver for saving game state. */
    MapSaver mapSaver;

    /**
     * Constructs a new KeyBoard object with the specified game world.
     *
     * @param gameWorld the game world associated with the keyboard input
     */
    public KeyBoard(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                gameWorld.hunter.state = MovementState.MOVING;
                gameWorld.hunter.dir = -1;
                gameWorld.hunter.revers = 0;
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.hunter.state = MovementState.MOVING;
                gameWorld.hunter.dir = 1;
                gameWorld.hunter.revers = -90;
                break;
            case KeyEvent.VK_UP:
                gameWorld.hunter.state = MovementState.JUMPING;
                break;
            case KeyEvent.VK_SPACE:
                gameWorld.hunter.state = MovementState.ATTACKING;
                break;
            case KeyEvent.VK_E:
                mapSaver = new MapSaver(gameWorld.hunter, gameWorld.blocks, gameWorld.enemies, gameWorld.golds, gameWorld.npc,gameWorld.loots);
                mapSaver.saveMap("src/main/java/org/example/view/lastSave.json");
                JOptionPane.showMessageDialog(gameWorld, "Game Saved");
                break;
            case KeyEvent.VK_Q:

                gameWorld.hunter.craft();

                break;
                case KeyEvent.VK_P:
                    gameWorld.hunter.usePowerPotion();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameWorld.hunter.state = MovementState.IDLE;
    }
}
