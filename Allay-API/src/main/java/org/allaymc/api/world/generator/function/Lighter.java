package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.LightContext;

import java.util.function.Function;

/**
 * Allay Project 2024/6/16
 *
 * @author daoge_cmd
 */
public interface Lighter extends Function<LightContext, Boolean>, GenerateFunction {

}
