package org.allaymc.api.item.initinfo;

import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the initialization information for an item stack.
 *
 * @author daoge_cmd
 */
public interface ItemStackInitInfo extends ComponentInitInfo {

    /**
     * Get the count of the item stack.
     *
     * @return the count of the item stack.
     */
    int count();

    /**
     * Get the meta of the item stack.
     *
     * @return the meta of the item stack.
     */
    int meta();

    /**
     * Get the extra tag of the item stack.
     *
     * @return the extra tag of the item stack.
     */
    NbtMap extraTag();

    /**
     * Get the stack network id of the item stack.
     *
     * @return the stack network id of the item stack.
     */
    int stackNetworkId();

    /**
     * Whether to auto assign the stack network id.
     *
     * @return whether to auto assign the stack network id.
     */
    boolean autoAssignStackNetworkId();

    /**
     * Get the item type of the item stack.
     *
     * @return the item type of the item stack.
     */
    ItemType<?> getItemType();

    /**
     * Set the item type of the item stack.
     *
     * @param itemType the item type of the item stack.
     */
    @ApiStatus.OverrideOnly
    void setItemType(ItemType<?> itemType);
}
