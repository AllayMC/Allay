package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface EnchantTableContainer extends BlockContainer {
    int INPUT_SLOT = 0;
    int MATERIAL_SLOT = 1;

    ItemStack getInput();

    ItemStack getMaterial();
}
