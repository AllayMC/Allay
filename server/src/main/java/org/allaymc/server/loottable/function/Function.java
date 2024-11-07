package org.allaymc.server.loottable.function;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface Function {
    /**
     * Applies this function to the given ItemStack.
     *
     * @param itemStack The ItemStack to apply the function to.
     */
    void apply(ItemStack itemStack);
}
