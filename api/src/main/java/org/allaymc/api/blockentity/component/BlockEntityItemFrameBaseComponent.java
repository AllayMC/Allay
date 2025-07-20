package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.jetbrains.annotations.Range;

/**
 * @author daoge_cmd
 */
public interface BlockEntityItemFrameBaseComponent extends BlockEntityBaseComponent {
    /**
     * Gets the item stack in the item frame.
     *
     * @return the item stack in the item frame, or {@link ItemAirStack#AIR_STACK} if there is no item.
     */
    ItemStack getItemStack();

    /**
     * Sets the item stack in the item frame.
     *
     * @param itemStack the item stack to set in the item frame, or {@link ItemAirStack#AIR_STACK} to remove the item.
     */
    void setItemStack(ItemStack itemStack);

    /**
     * Clears the item stack in the item frame, setting it to {@link ItemAirStack#AIR_STACK}.
     */
    default void clearItemStack() {
        setItemStack(ItemAirStack.AIR_STACK);
    }

    /**
     * Gets the rotation of the item in the item frame.
     *
     * @return the rotation of the item, from 0 to 7, where 0 is no rotation and 7 is a full rotation.
     */
    @Range(from = 0, to = 7)
    byte getItemRotation();

    /**
     * Sets the rotation of the item in the item frame.
     *
     * @param itemRotation the rotation of the item, from 0 to 7, where 0 is no rotation and 7 is a full rotation.
     */
    void setItemRotation(@Range(from = 0, to = 7) byte itemRotation);
}
