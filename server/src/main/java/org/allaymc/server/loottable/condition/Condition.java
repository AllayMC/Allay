package org.allaymc.server.loottable.condition;

import org.allaymc.server.loottable.context.Context;

/**
 * @author daoge_cmd
 */
public interface Condition<CONTEXT_TYPE extends Context> {
    boolean test(CONTEXT_TYPE context);
}
