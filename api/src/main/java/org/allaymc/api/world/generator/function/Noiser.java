package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.NoiseContext;

/**
 * Noiser is responsible for generating basic noise in the world, such as biome, terrain and
 * some other noise-based features. It is called in the first generation stage. Noiser can
 * only access the current chunk.
 *
 * @author daoge_cmd
 */
public interface Noiser extends GenerateFunction {
    boolean apply(NoiseContext noiseContext);
}
