package org.allaymc.api.loottable.entry;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.loottable.context.Context;

import java.util.List;
import java.util.Set;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public record Entries<CONTEXT_TYPE extends Context>(
        List<Entry<CONTEXT_TYPE>> entries
) {
    public Set<ItemStack> loot(CONTEXT_TYPE context) {
        int weightSum = 0;
        for (Entry<CONTEXT_TYPE> entry : entries) {
            weightSum += entry.getWeight();
        }
        // TODO
    }
}
