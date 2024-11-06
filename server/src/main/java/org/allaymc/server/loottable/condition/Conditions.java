package org.allaymc.server.loottable.condition;

import org.allaymc.server.loottable.context.Context;

import java.util.List;

/**
 * @author daoge_cmd
 */
public record Conditions<CONTEXT_TYPE extends Context>(
        List<Condition<CONTEXT_TYPE>> conditions
) {
    /**
     * Tests the conditions with the given context.
     *
     * @param context The context to test the conditions with.
     *
     * @return {@code true} if the conditions pass, {@code false} otherwise.
     */
    public boolean test(CONTEXT_TYPE context) {
        return conditions.stream().allMatch(condition -> condition.test(context));
    }
}
