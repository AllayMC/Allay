package org.allaymc.api.entity.component;

import org.allaymc.api.utils.DyeColor;

/**
 * Component for entities that can be dyed (sheep, cat collar, etc.).
 *
 * @author daoge_cmd
 */
public interface EntityDyeableComponent extends EntityComponent {

    /**
     * Get the current dye color of this entity.
     *
     * @return the dye color
     */
    DyeColor getColor();

    /**
     * Set the dye color of this entity.
     *
     * @param color the dye color to set
     */
    void setColor(DyeColor color);
}
