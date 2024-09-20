package org.allaymc.server.loottable;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.loottable.Rolls;
import org.allaymc.api.loottable.condition.Conditions;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.entry.Entries;

import java.util.HashSet;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public record Pool<CONTEXT_TYPE extends Context>(
        Conditions<CONTEXT_TYPE> conditions,
        Rolls rolls,
        Entries<CONTEXT_TYPE> entries
) {
    public Set<ItemStack> loot(CONTEXT_TYPE context) {
        if (!conditions.test(context)) return Set.of();
        Set<ItemStack> items = new HashSet<>();
        for (var i = 0; i < rolls.getRolls(); i++) {
            items.addAll(entries.loot(context));
        }
        return items;
    }
}
