package org.allaymc.api.loottable;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.loottable.condition.Conditions;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.entry.Entries;

import java.util.Set;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public record Pool<CONTEXT_TYPE extends Context>(
        Conditions<CONTEXT_TYPE> conditions,
        Rolls rolls,
        Entries<CONTEXT_TYPE> entries
) {
    private static final Set<ItemStack> EMPTY = Set.of();

    Set<ItemStack> loot(CONTEXT_TYPE context) {
        if (!conditions.test(context)) return EMPTY;
        var r = rolls.getRolls();

    }
}
