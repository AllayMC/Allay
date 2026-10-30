package org.allaymc.api.entity.component;

import org.allaymc.api.utils.DyeColor;

/**
 * {@code EntityDyeableComponent} exposes the dye color used by an entity's
 * appearance. The server implementation stores the selected color for NBT
 * persistence and broadcasts entity state when the color changes, allowing
 * viewers to observe the updated appearance.
 *
 * @author daoge_cmd
 */
public interface EntityDyeableComponent extends EntityComponent {

    /**
     * Returns the dye color currently used for this entity's appearance.
     */
    DyeColor getColor();

    /**
     * Changes the entity's dye color and broadcasts its updated state. Callers
     * supply a usable {@code DyeColor}; the server stores the reference
     * directly and later serializes its ordinal to NBT.
     */
    void setColor(DyeColor color);
}
