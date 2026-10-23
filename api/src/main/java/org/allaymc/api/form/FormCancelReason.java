package org.allaymc.api.form;

/**
 * {@code FormCancelReason} identifies why a viewer returned cancellation rather than form data.
 * Forms deliver this reason to close callbacks, allowing applications to distinguish deliberate
 * dismissal from a client that was busy and could not display the form.
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
