package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * EnchantTableContainer represents a container for an enchantment table block.
 *
 * @author daoge_cmd
 */
public interface EnchantTableContainer extends BlockContainer {
    /**
     * The constant representing the input slot index in the enchantment table container.
     */
    int INPUT_SLOT = 0;

    /**
     * The constant representing the material slot index in the enchantment table container.
     */
    int MATERIAL_SLOT = 1;

    /**
     * Gets the item in the input slot of the enchantment table.
     *
     * @return the ItemStack in the input slot
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Gets the item in the material slot of the enchantment table.
     *
     * @return the ItemStack in the material slot
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }
}
