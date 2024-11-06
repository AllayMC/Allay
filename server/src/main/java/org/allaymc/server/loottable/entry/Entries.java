package org.allaymc.server.loottable.entry;

import org.allaymc.api.item.ItemStack;
import org.allaymc.server.loottable.context.Context;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public record Entries<CONTEXT_TYPE extends Context>(List<Entry<CONTEXT_TYPE>> entries) {
    public Set<ItemStack> loot(CONTEXT_TYPE context) {
        var validEntries = entries.stream()
                .filter(entry -> entry.test(context))
                .toList();
        var weightSum = validEntries.stream().mapToInt(Entry::getWeight).sum();

        var rand = ThreadLocalRandom.current().nextInt(weightSum) + 1;
        for (var entry : validEntries) {
            rand -= entry.getWeight();
            if (rand <= 0) {
                return entry.loot(context);
            }
        }
        // Won't reach here
        throw new RuntimeException();
    }
}
