package org.allaymc.api.loottable.function;

import org.allaymc.api.item.ItemStack;

import java.util.List;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public record Functions(
        List<Function> functions
) {
    public void apply(ItemStack itemStack) {
        for (Function function : functions) {
            function.apply(itemStack);
        }
    }
}
