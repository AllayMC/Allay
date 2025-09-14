package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface LoomContainer extends BlockContainer {
    int INPUT_SLOT = 0;
    int DYE_SLOT = 1;
    int MATERIAL_SLOT = 2;
    int RESULT_SLOT = 3;

    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    default ItemStack getDye() {
        return getItemStack(DYE_SLOT);
    }

    default void setDye(ItemStack itemStack) {
        setItemStack(DYE_SLOT, itemStack);
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
