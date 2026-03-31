package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Property;
import org.allaymc.api.ddui.type.CustomFormScreen;
import org.jetbrains.annotations.ApiStatus;

/**
 * Base contract of elements that can be attached to a {@link CustomFormScreen}.
 *
 * @author daoge_cmd | SerenityJS
 */
public sealed interface DDUIElement permits ValueElement, ElementBase {
    /**
     * Gets the visibility property of this element.
     * <p>
     * This property controls both the initial visibility sent to the client and later runtime updates.
     *
     * @return the visibility property
     */
    Property<Boolean> getVisible();

    /**
     * Attaches this element to a screen slot.
     *
     * @param screen the owning screen
     * @param index  the zero-based layout index
     */
    @ApiStatus.Internal
    void bind(CustomFormScreen screen, int index);

    /**
     * Gets the owning screen of this element.
     *
     * @return the owning screen, or {@code null} if this element has not been attached yet
     */
    @ApiStatus.Internal
    CustomFormScreen getScreen();

    /**
     * Gets the zero-based layout index assigned to this element.
     *
     * @return the assigned index, or {@code -1} if this element has not been attached yet
     */
    @ApiStatus.Internal
    int getIndex();
}
