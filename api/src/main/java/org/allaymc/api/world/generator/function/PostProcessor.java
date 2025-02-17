package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.PostProcessContext;

/**
 * @author daoge_cmd
 */
public interface PostProcessor extends GenerateFunction {
    boolean apply(PostProcessContext postProcessContext);
}
