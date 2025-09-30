package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.PopulateContext;

/**
 * Populator is responsible for populating features that may across multiple chunks. It is
 * called after {@link Noiser}. Populator can access the current chunk and the eight
 * adjacent and diagonal chunks next to it.
 *
 * @author daoge_cmd
 */
public interface Populator extends GenerateFunction {
    boolean apply(PopulateContext populateContext);
}
