package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
public class AnvilContainer extends BlockContainer {
    public static final int INPUT_SLOT = 0;
    public static final int MATERIAL_SLOT = 1;
    public static final int RESULT_SLOT = 2;

    public AnvilContainer() {
        super(FullContainerType.ANVIL);
    }

    public ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    public void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    public ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    public void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    public ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    public void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
