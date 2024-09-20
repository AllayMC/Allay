package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.NoiseContext;

/**
 * @author daoge_cmd
 */
public interface Noiser extends GenerateFunction {
    boolean apply(NoiseContext noiseContext);
}
