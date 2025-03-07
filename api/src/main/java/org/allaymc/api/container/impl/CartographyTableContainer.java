package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
public class CartographyTableContainer extends BlockContainer {
    public static final int INPUT_SLOT = 0;
    public static final int ADDITIONAL_SLOT = 1;
    public static final int RESULT_SLOT = 2;

    public CartographyTableContainer() {
        super(FullContainerType.GRINDSTONE);
    }

    public ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    public void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    public ItemStack getAdditional() {
        return getItemStack(ADDITIONAL_SLOT);
    }

    public void setAdditional(ItemStack itemStack) {
        setItemStack(ADDITIONAL_SLOT, itemStack);
    }

    public ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    public void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
