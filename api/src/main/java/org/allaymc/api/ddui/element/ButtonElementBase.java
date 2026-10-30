package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;
import org.allaymc.api.ddui.type.CustomFormScreen;

import java.util.function.Consumer;

/**
 * {@code ButtonElementBase} supplies label configuration and click callbacks for DDUI buttons.
 * Concrete button types share the same template properties, and callback registration returns the
 * owning custom-form screen so callers can continue configuring its layout.
 *
 * @author daoge_cmd | SerenityJS
 */
abstract class ButtonElementBase<E extends ButtonElementBase<E>> extends ElementBase<E> {
    /**
     * Control label template property.
     */
    private final Property<String> label = new Property<>("");
    /**
     * {@code onClick} receives the session for a client click. The callback starts as a no-op and
     * its failures propagate to the caller.
     */
    private Consumer<DDUIScreenSession> onClick = session -> {
    };

    /**
     * Returns this element's mutable {@code label} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    public Property<String> getLabel() {
        return label;
    }

    /**
     * Stores the supplied label template value and returns this element for further configuration.
     * This operation clears that property's observable binding and does not send updates to
     * existing sessions.
     */
    public E label(String label) {
        this.label.set(label);
        return self();
    }

    /**
     * Binds the label template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public E label(Observable<String> label) {
        this.label.bind(label);
        return self();
    }

    /**
     * Returns the currently configured callback for this button's client clicks.
     */
    public Consumer<DDUIScreenSession> getOnClick() {
        return onClick;
    }

    /**
     * Stores the client-click callback and returns this button's owning screen. The element must
     * already be attached; an attachment failure occurs after storing the callback, and callback
     * exceptions propagate during click handling.
     */
    public CustomFormScreen onClick(Consumer<DDUIScreenSession> onClick) {
        this.onClick = onClick;
        return requireScreen();
    }
}
