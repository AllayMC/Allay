package org.allaymc.api.blockentity.component;

import org.allaymc.api.utils.DyeColor;

/**
 * {@code BlockEntityBedBaseComponent} exposes the dye color stored for a bed
 * block entity. The server keeps this color in NBT and propagates color changes
 * to the other bed half when its block entity supports the same capability.
 *
 * @author harryxi
 */
public interface BlockEntityBedBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the dye color currently stored for this bed half.
     */
    DyeColor getColor();

    /**
     * Stores the bed color and synchronizes the paired bed half when available.
     * The server updates viewers after propagation; callers supply a usable dye
     * color because later serialization dereferences the stored value.
     */
    void setColor(DyeColor color);
}
