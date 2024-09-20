package org.allaymc.api.loottable.condition;

import org.allaymc.api.loottable.context.Context;

/**
 * @author daoge_cmd
 */
public interface Condition<CONTEXT_TYPE extends Context> {
    /**
     * Tests the condition with the given context.
     *
     * @param context The context to test the condition with.
     *
     * @return {@code true} if the condition passes, {@code false} otherwise.
     */
    boolean test(CONTEXT_TYPE context);
}
