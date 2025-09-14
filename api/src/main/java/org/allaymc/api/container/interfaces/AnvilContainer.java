package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface AnvilContainer extends BlockContainer {
    int INPUT_SLOT = 0;
    int MATERIAL_SLOT = 1;
    int RESULT_SLOT = 2;

    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    default void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
