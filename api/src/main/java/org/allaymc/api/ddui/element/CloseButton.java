package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * Special button rendered in the dedicated close-button slot of a custom form.
 * <p>
 * When clicked, the viewer's current DDUI session is closed after the button callback has been processed.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class CloseButton extends ButtonElementBase<CloseButton> {
    /**
     * Creates a close button with the default label {@code Close}.
     */
    public CloseButton() {
        label("Close");
    }

    /**
     * Creates a close button with a literal label.
     *
     * @param label the close-button label
     */
    public CloseButton(String label) {
        label(label);
    }

    /**
     * Creates a close button bound to an observable label.
     *
     * @param label the label observable
     */
    public CloseButton(Observable<String> label) {
        label(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CloseButton self() {
        return this;
    }
}
