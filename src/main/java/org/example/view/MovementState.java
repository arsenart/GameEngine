package org.example.view;

/**
 * Represents the possible movement states of a character in the game.
 */
public enum MovementState {
    /** Character is idle (not moving). */
    IDLE,
    /** Character is moving horizontally. */
    MOVING,
    /** Character is moving downwards (falling). */
    MOVING_DOWN,
    /** Character is jumping. */
    JUMPING,
    /** Character is attacking. */
    ATTACKING
}
