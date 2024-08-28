package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.PopulateContext;

/**
 * Allay Project 2024/6/16
 *
 * @author daoge_cmd
 */
public interface Populator extends GenerateFunction {
    boolean apply(PopulateContext populateContext);
}
