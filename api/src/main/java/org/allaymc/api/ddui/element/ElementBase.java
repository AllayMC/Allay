package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;
import org.allaymc.api.ddui.type.CustomFormScreen;

/**
 * {@code ElementBase} stores screen attachment and visibility configuration shared by custom-form
 * elements. Attachment assigns an owning screen and index once; fluent configuration uses the
 * concrete element type, while operations returning a screen require prior attachment.
 *
 * @author daoge_cmd | SerenityJS
 */
non-sealed abstract class ElementBase<E extends ElementBase<E>> implements DDUIElement {
    /**
     * {@code screen} holds the owning template after attachment; {@code null} marks an element
     * whose attachment has not been established.
     */
    private CustomFormScreen screen;
    /**
     * Zero-based layout slot, or {@code -1} before attachment or for a dedicated close button.
     */
    private int index = -1;
    /**
     * Element visibility template property.
     */
    private final Property<Boolean> visible = new Property<>(true);

    /**
     * Returns this element's mutable {@code visible} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    @Override
    public Property<Boolean> getVisible() {
        return visible;
    }

    /**
     * Stores the supplied visible template value and returns this element for further
     * configuration. This operation clears that property's observable binding and does not send
     * updates to existing sessions.
     */
    public E visible(boolean visible) {
        this.visible.set(visible);
        return self();
    }

    /**
     * Binds the visible template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public E visible(Observable<Boolean> visible) {
        this.visible.bind(visible);
        return self();
    }

    /**
     * Returns the screen template to which this element is attached. The result is {@code null}
     * before attachment, and callers receive the owning template rather than a runtime session.
     */
    @Override
    public CustomFormScreen getScreen() {
        return screen;
    }

    /**
     * Returns the zero-based layout index assigned when this element was attached. The value is
     * {@code -1} before attachment and for a dedicated close button that occupies no layout slot.
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Attaches this element to the supplied screen and records its layout index. An element with an
     * existing non-{@code null} owner rejects reattachment, preserving its original owner and
     * index.
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
     * Returns this element's owning screen for fluent screen configuration. The element must
     * already be attached; an unattached element raises {@code IllegalStateException} rather than
     * returning a missing owner.
     */
    protected final CustomFormScreen requireScreen() {
        if (screen == null) {
            throw new IllegalStateException("Element is not attached to a screen");
        }
        return screen;
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    protected abstract E self();
}
