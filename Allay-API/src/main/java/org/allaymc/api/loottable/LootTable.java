package org.allaymc.api.loottable;

import org.allaymc.api.loottable.context.Context;

import java.util.List;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public record LootTable<CONTEXT_TYPE extends Context>(
        LootTableType<CONTEXT_TYPE> lootTableType,
        List<Pool<CONTEXT_TYPE>> pools
) {
}
