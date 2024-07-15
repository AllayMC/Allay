package org.allaymc.server.loottables.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.loottable.condition.Conditions;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.entry.Entry;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public abstract class BaseEntry<CONTEXT_TYPE extends Context> implements Entry<CONTEXT_TYPE> {
    protected String name;
    protected int weight;
    protected Conditions<CONTEXT_TYPE> conditions;
}
