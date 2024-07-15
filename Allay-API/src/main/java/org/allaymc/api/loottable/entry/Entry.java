package org.allaymc.api.loottable.entry;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.loottable.condition.Conditions;
import org.allaymc.api.loottable.context.Context;

import java.util.Set;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public interface Entry<CONTEXT_TYPE extends Context> {
    String getType();

    String getName();

    int getWeight();

    Conditions<CONTEXT_TYPE> getConditions();

    Set<ItemStack> loot(CONTEXT_TYPE context);

    default boolean test(CONTEXT_TYPE context) {
        return getConditions().test(context);
    }
}
