package org.allaymc.api.form;

/**
 * Represents the reason for a form being cancelled.
 */
public enum FormCancelReason {
    /**
     * The viewer closed the form.
     */
    CLOSED,
    /**
     * The viewer was unable to handle the form (e.g. when the viewer is in the pause menu).
     */
    BUSY
}
