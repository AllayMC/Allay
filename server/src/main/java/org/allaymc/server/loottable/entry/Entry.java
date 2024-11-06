package org.allaymc.server.loottable.entry;

import org.allaymc.api.item.ItemStack;
import org.allaymc.server.loottable.condition.Conditions;
import org.allaymc.server.loottable.context.Context;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface Entry<CONTEXT_TYPE extends Context> {
    /**
     * Loot method that returns a set of ItemStack objects.
     *
     * @param context The context to loot with.
     *
     * @return A set of ItemStack objects.
     */
    Set<ItemStack> loot(CONTEXT_TYPE context);

    /**
     * Tests the entry's conditions with the given context.
     *
     * @param context The context to test the conditions with.
     *
     * @return {@code true} if the conditions pass, {@code false} otherwise.
     */
    default boolean test(CONTEXT_TYPE context) {
        return getConditions().test(context);
    }

    /**
     * Returns the name of the entry.
     *
     * @return The name of the entry.
     */
    String getName();

    /**
     * Returns the weight of the entry.
     *
     * @return The weight of the entry.
     */
    int getWeight();

    /**
     * Returns the conditions of the entry.
     *
     * @return The conditions of the entry.
     */
    Conditions<CONTEXT_TYPE> getConditions();
}
