package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.PostProcessContext;

/**
 * {@code PostProcessor} is responsible for applying post-processing features to
 * the world. It is called in the last generation stage after {@link Noiser} and
 * {@link Populator}. {@code PostProcessor} can only access the current chunk.
 * Its context retains the shared unsafe chunk used by the preceding stages.
 *
 * @author daoge_cmd
 */
public interface PostProcessor extends GenerateFunction {
    /**
     * Applies this stage function to the shared chunk data exposed by its
     * context. Implementations must stay within that context's allowed region;
     * the current runtime invokes all configured functions and ignores the
     * returned boolean.
     */
    boolean apply(PostProcessContext postProcessContext);
}
