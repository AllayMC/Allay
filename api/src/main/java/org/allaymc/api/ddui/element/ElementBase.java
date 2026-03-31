package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BindableValue;
import org.allaymc.api.ddui.type.CustomFormScreen;

/**
 * Shared base implementation for DDUI elements.
 *
 * @param <E> the concrete element type
 *
 * @author daoge_cmd | SerenityJS
 */
non-sealed abstract class ElementBase<E extends ElementBase<E>> implements DDUIElement {
    private CustomFormScreen screen;
    private int index = -1;
    private final BindableValue<Boolean> visible = new BindableValue<>(true);

    /**
     * Gets whether this element is visible by default.
     *
     * @return {@code true} if this element is visible
     */
    public boolean isVisible() {
        return visible.value();
    }

    /**
     * Gets the observable bound to this element's visibility.
     *
     * @return the visibility observable, or {@code null} if visibility is not observable-backed
     */
    public Observable<Boolean> getVisibleObservable() {
        return visible.observable();
    }

    /**
     * Sets the visibility of this element.
     *
     * @param visible the visibility value
     * @return this element
     */
    public E visible(boolean visible) {
        this.visible.set(visible);
        return self();
    }

    /**
     * Binds this element's visibility to an observable.
     *
     * @param visible the visibility observable
     * @return this element
     */
    public E visible(Observable<Boolean> visible) {
        this.visible.bind(visible);
        return self();
    }

    /**
     * Gets the owning screen of this element.
     *
     * @return the owning screen, or {@code null} if this element has not been attached yet
     */
    @Override
    public CustomFormScreen getScreen() {
        return screen;
    }

    /**
     * Gets the layout index assigned to this element.
     *
     * @return the assigned index, or {@code -1} if this element has not been attached yet
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Attaches this element to a custom-form screen.
     *
     * @param screen the owning screen
     * @param index  the zero-based layout index
     */
    @Override
    public void bind(CustomFormScreen screen, int index) {
        if (this.screen != null) {
            throw new IllegalStateException("Element is already attached to a screen");
        }
        this.screen = screen;
        this.index = index;
    }

    /**
     * Gets the owning screen and fails if this element is not attached yet.
     *
     * @return the owning screen
     */
    protected final CustomFormScreen requireScreen() {
        if (screen == null) {
            throw new IllegalStateException("Element is not attached to a screen");
        }
        return screen;
    }

    /**
     * Returns this element as its concrete type.
     *
     * @return this element
     */
    protected abstract E self();
}
