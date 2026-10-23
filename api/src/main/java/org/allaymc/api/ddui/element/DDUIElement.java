package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Property;
import org.allaymc.api.ddui.type.CustomFormScreen;
import org.jetbrains.annotations.ApiStatus;

/**
 * {@code DDUIElement} identifies a handle attached to a custom-form screen and exposes its
 * visibility property. A runtime session maps element identity to its compiled paths; callers use
 * the same handle for later session value, visibility, or disabled-state operations.
 *
 * @author daoge_cmd | SerenityJS
 */
public sealed interface DDUIElement permits ValueElement, ElementBase {
    /**
     * Returns the mutable visibility property used when this element is compiled. Observable
     * bindings can drive later runtime visibility; changing a literal template value does not
     * update an existing session.
     */
    Property<Boolean> getVisible();

    /**
     * Attaches this element to its owning template and records the layout position. Callers use
     * {@code -1} for a dedicated close button; a handle with an existing non-{@code null} owner
     * rejects reattachment.
     */
    @ApiStatus.Internal
    void bind(CustomFormScreen screen, int index);

    /**
     * Returns the screen template to which this element has been attached. The result is
     * {@code null} before attachment; runtime sessions retain the same template and handle
     * identities.
     */
    @ApiStatus.Internal
    CustomFormScreen getScreen();

    /**
     * Returns the zero-based layout index assigned during attachment to a screen. The result is
     * {@code -1} before attachment and for a dedicated close button outside the layout slots.
     */
    @ApiStatus.Internal
    int getIndex();
}
