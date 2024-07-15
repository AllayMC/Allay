package org.allaymc.api.loottable;

import lombok.Builder;
import org.allaymc.api.loottable.condition.Condition;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.function.Function;

import java.util.Map;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public class LootTableType<CONTEXT_TYPE extends Context> {
    protected Map<String, Function> functions;
    protected Map<String, Condition> conditions;

    @Builder
    public LootTableType(
            Map<String, Function> functions,
            Map<String, Condition> conditions
    ) {
        this.functions = functions;
        this.conditions = conditions;
    }

    public Function getFunction(String name) {
        return functions.get(name);
    }

    public Condition getCondition(String name) {
        return conditions.get(name);
    }
}
