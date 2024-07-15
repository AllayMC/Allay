package org.allaymc.api.loottable.function;

import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public interface Function {
    void apply(ItemStack itemStack);
    String getName();
}
