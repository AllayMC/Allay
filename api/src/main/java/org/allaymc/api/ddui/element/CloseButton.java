package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * A DDUI close-button element.
 *
 * @author OpenAI
 */
public final class CloseButton extends ButtonElementBase<CloseButton> {
    /**
     * Creates an unlabeled close button.
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
