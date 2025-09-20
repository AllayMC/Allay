package org.allaymc.api.form;

/**
 * Represents the reason for a form being cancelled.
 */
public enum FormCancelReason {
    /**
     * The player closed the form.
     */
    PLAYER_CLOSED,
    /**
     * The player was unable to handle the form (e.g. when the player is in the pause menu).
     */
    PLAYER_BUSY
}
