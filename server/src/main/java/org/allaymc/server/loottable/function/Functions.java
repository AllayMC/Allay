package org.allaymc.server.loottable.function;

import org.allaymc.api.item.ItemStack;

import java.util.List;

/**
 * @author daoge_cmd
 */
public record Functions(List<Function> functions) {
    /**
     * Applies each function in the list to the given item stack.
     *
     * @param itemStack The item stack to apply the functions to.
     */
    public void apply(ItemStack itemStack) {
        functions.forEach(function -> function.apply(itemStack));
    }
}
