package org.allaymc.server.loottable.condition;

import org.allaymc.server.loottable.context.Context;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public interface Condition<CONTEXT_TYPE extends Context> {
    boolean test(CONTEXT_TYPE context);
}
