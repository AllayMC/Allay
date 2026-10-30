package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * {@code CloseButton} represents the dedicated close control of a custom-form screen. Session
 * compilation places it outside the layout and permits only one such control. Its click callback
 * runs before closure, so a callback exception prevents the subsequent silent close.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class CloseButton extends ButtonElementBase<CloseButton> {
    /**
     * Creates a dedicated close-button template with the label {@code "Close"}.
     */
    public CloseButton() {
        label("Close");
    }

    /**
     * Creates a closebutton template with the supplied literal label value.
     */
    public CloseButton(String label) {
        label(label);
    }

    /**
     * Creates a closebutton template using the supplied observable configuration. Construction
     * captures current source values; runtime sessions subscribe when compiled, and later literal
     * template changes do not replace those existing subscriptions.
     */
    public CloseButton(Observable<String> label) {
        label(label);
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected CloseButton self() {
        return this;
    }
}
