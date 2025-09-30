package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.PostProcessContext;

/**
 * PostProcessor is responsible for applying post-processing features to the world. It is
 * called in the last generation stage after {@link Noiser} and {@link Populator}. PostProcessor
 * can only access the current chunk.
 *
 * @author daoge_cmd
 */
public interface PostProcessor extends GenerateFunction {
    boolean apply(PostProcessContext postProcessContext);
}
