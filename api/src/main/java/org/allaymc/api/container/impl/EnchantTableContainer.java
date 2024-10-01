package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public class EnchantTableContainer extends BlockContainer {
    public static final int INPUT_SLOT = 0;
    public static final int MATERIAL_SLOT = 1;

    public EnchantTableContainer() {
        super(FullContainerType.ENCHANT_TABLE);
    }

    public ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    public ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }
}
