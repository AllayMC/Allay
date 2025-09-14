package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface StonecutterContainer extends BlockContainer {
    int INPUT_SLOT = 0;
    int RESULT_SLOT = 1;

    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
