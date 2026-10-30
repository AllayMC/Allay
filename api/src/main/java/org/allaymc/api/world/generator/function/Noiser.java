package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.NoiseContext;

/**
 * {@code Noiser} is responsible for generating basic noise in the world, such
 * as biome, terrain and some other noise-based features. It is called in the
 * first generation stage. {@code Noiser} can only access the current chunk.
 *
 * @author daoge_cmd
 */
public interface Noiser extends GenerateFunction {
    /**
     * Applies this stage function to the shared chunk data exposed by its
     * context. Implementations must stay within that context's allowed region;
     * the current runtime invokes all configured functions and ignores the
     * returned boolean.
     */
    boolean apply(NoiseContext noiseContext);
}
