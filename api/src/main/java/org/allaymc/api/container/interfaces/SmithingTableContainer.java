package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface SmithingTableContainer extends BlockContainer, RecipeContainer {
    int INPUT_SLOT = 0;
    int MATERIAL_SLOT = 1;
    int TEMPLATE_SLOT = 2;
    int RESULT_SLOT = 3;

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

    default ItemStack getTemplate() {
        return getItemStack(TEMPLATE_SLOT);
    }

    default void setTemplate(ItemStack itemStack) {
        setItemStack(TEMPLATE_SLOT, itemStack);
    }

    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
