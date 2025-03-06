package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
public class StonecutterContainer extends BlockContainer {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;

    public StonecutterContainer() {
        super(FullContainerType.STONECUTTER);
    }

    public ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    public void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    public ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    public void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
