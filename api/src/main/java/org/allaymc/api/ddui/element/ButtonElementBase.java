package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;
import org.allaymc.api.ddui.type.CustomFormScreen;

import java.util.function.Consumer;

/**
 * Shared base implementation for DDUI button elements.
 *
 * @param <E> the concrete element type
 *
 * @author daoge_cmd | SerenityJS
 */
abstract class ButtonElementBase<E extends ButtonElementBase<E>> extends ElementBase<E> {
    private final Property<String> label = new Property<>("");
    private Consumer<DDUIScreenSession> onClick = session -> {
    };

    /**
     * Gets the label property of this button.
     *
     * @return the label property
     */
    public Property<String> getLabel() {
        return label;
    }

    /**
     * Sets the button label.
     *
     * @param label the button label
     * @return this element
     */
    public E label(String label) {
        this.label.set(label);
        return self();
    }

    /**
     * Binds the button label to an observable.
     *
     * @param label the label observable
     * @return this element
     */
    public E label(Observable<String> label) {
        this.label.bind(label);
        return self();
    }

    /**
     * Gets the callback invoked when the viewer clicks this button.
     *
     * @return the click callback
     */
    public Consumer<DDUIScreenSession> getOnClick() {
        return onClick;
    }

    /**
     * Sets the callback invoked when the viewer clicks this button.
     * <p>
     * The button must already be attached to a screen because this method returns the owning screen for continued
     * fluent configuration.
     *
     * @param onClick the click callback
     * @return the owning screen
     */
    public CustomFormScreen onClick(Consumer<DDUIScreenSession> onClick) {
        this.onClick = onClick;
        return requireScreen();
    }
}
