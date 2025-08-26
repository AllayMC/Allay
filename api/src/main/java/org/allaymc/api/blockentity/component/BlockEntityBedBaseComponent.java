package org.allaymc.api.blockentity.component;

import org.allaymc.api.blockentity.data.BedColor;

public interface BlockEntityBedBaseComponent extends BlockEntityBaseComponent {
    /**
     * get the color of beds
     */
    BedColor getColor();

    /**
     * set the color of beds
     */
    void setColor(BedColor color);
}
