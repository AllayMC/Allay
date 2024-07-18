package org.allaymc.server.loottable.condition;

import org.allaymc.server.loottable.context.Context;

import java.util.List;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public record Conditions<CONTEXT_TYPE extends Context>(
        List<Condition<CONTEXT_TYPE>> conditions
) {
    public boolean test(CONTEXT_TYPE context) {
        return conditions.stream().allMatch(condition -> condition.test(context));
    }
}
