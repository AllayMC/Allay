package org.allaymc.api.blockentity.component;

import org.allaymc.api.utils.DyeColor;

/**
 * @author harryxi
 */
public interface BlockEntityBedBaseComponent extends BlockEntityBaseComponent {
    /**
     * Gets the color of this bed.
     *
     * @return the color of this bed.
     */
    DyeColor getColor();

    /**
     * Sets the color of this bed.
     *
     * @param color the color to set.
     */
    void setColor(DyeColor color);
}
