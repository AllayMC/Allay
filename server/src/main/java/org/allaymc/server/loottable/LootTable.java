package org.allaymc.server.loottable;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.loottable.LootTableType;
import org.allaymc.api.loottable.context.Context;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public record LootTable<CONTEXT_TYPE extends Context>(
        LootTableType<CONTEXT_TYPE> lootTableType,
        List<Pool<CONTEXT_TYPE>> pools
) {
    public Set<ItemStack> loot(CONTEXT_TYPE context) {
        return pools.stream()
                .flatMap(pool -> pool.loot(context).stream())
                .collect(Collectors.toSet());
    }
}
