package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.LightContext;

/**
 * @author daoge_cmd
 */
public interface Lighter extends GenerateFunction {
    boolean apply(LightContext lightContext);
}
