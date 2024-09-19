package org.allaymc.server.loottable.entry;

import org.allaymc.api.item.ItemStack;
import org.allaymc.server.loottable.condition.Conditions;
import org.allaymc.server.loottable.context.Context;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface Entry<CONTEXT_TYPE extends Context> {
    Set<ItemStack> loot(CONTEXT_TYPE context);

    default boolean test(CONTEXT_TYPE context) {
        return getConditions().test(context);
    }

    String getName();

    int getWeight();

    Conditions<CONTEXT_TYPE> getConditions();
}
