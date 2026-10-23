package org.allaymc.api.ddui.element;

/**
 * {@code Spacer} represents vertical separation in a custom-form layout. It contributes a
 * visibility property but no editable value, allowing callers to space groups of controls and
 * change that spacing's visibility through a session or observable binding.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Spacer extends ElementBase<Spacer> {
    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected Spacer self() {
        return this;
    }
}
