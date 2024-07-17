package org.allaymc.server.loottable;

import org.allaymc.api.item.ItemStack;
import org.allaymc.server.loottable.context.Context;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public record LootTable<CONTEXT_TYPE extends Context>(
        LootTableType<CONTEXT_TYPE> lootTableType,
        List<Pool<CONTEXT_TYPE>> pools
) {
    public Set<ItemStack> loot(CONTEXT_TYPE context) {
        Set<ItemStack> items = new HashSet<>();
        for (var pool : pools) {
            items.addAll(pool.loot(context));
        }
        return items;
    }
}
