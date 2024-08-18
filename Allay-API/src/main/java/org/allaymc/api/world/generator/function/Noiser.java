package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.NoiseContext;

/**
 * Allay Project 2024/6/16
 *
 * @author daoge_cmd
 */
public interface Noiser extends GenerateFunction {
    boolean apply(NoiseContext noiseContext);
}
