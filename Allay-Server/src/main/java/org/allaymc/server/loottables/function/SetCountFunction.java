package org.allaymc.server.loottables.function;

import lombok.AllArgsConstructor;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.loottable.function.Function;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class SetCountFunction implements Function {
    protected int count;
    @Override
    public void apply(ItemStack itemStack) {
        itemStack.setCount(count);
    }

    @Override
    public String getName() {
        return "set_count";
    }
}
