package org.allaymc.api.ddui.element;

/**
 * {@code Divider} represents a noninteractive separator in a custom-form layout. It contributes a
 * visibility property but no editable value, allowing callers to separate groups of controls and
 * change the separator's visibility through a session or observable binding.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Divider extends ElementBase<Divider> {
    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected Divider self() {
        return this;
    }
}
