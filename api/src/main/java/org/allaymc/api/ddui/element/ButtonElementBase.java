package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BindableValue;
import org.allaymc.api.ddui.session.DDUIScreenSession;
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
    private final BindableValue<String> label = new BindableValue<>("");
    private Consumer<DDUIScreenSession> onClick = session -> {
    };

    /**
     * Gets the button label.
     *
     * @return the button label
     */
    public String getLabel() {
        return label.value();
    }

    /**
     * Gets the observable bound to this button's label.
     *
     * @return the label observable, or {@code null} if the label is not observable-backed
     */
    public Observable<String> getLabelObservable() {
        return label.observable();
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
     * Gets the click callback.
     *
     * @return the click callback
     */
    public Consumer<DDUIScreenSession> getOnClick() {
        return onClick;
    }

    /**
     * Sets the click callback.
     *
     * @param onClick the click callback
     * @return the owning screen
     */
    public CustomFormScreen onClick(Consumer<DDUIScreenSession> onClick) {
        this.onClick = onClick;
        return requireScreen();
    }
}
