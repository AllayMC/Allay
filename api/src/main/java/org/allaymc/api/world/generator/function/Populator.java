package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.PopulateContext;

/**
 * {@code Populator} is responsible for populating features that may span multiple chunks. It is
 * called after {@link Noiser}. {@code Populator} can access the current chunk and the eight
 * adjacent and diagonal chunks next to it.
 *
 * @author daoge_cmd
 */
public interface Populator extends GenerateFunction {
    /**
     * Applies this stage function to the shared chunk data exposed by its
     * context. Implementations must stay within that context's allowed region;
     * the current runtime invokes all configured functions and ignores the
     * returned boolean.
     */
    boolean apply(PopulateContext populateContext);
}
