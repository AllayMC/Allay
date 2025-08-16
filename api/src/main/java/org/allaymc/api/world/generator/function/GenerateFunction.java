package org.allaymc.api.world.generator.function;

/**
 * @author daoge_cmd
 */
public interface GenerateFunction {
    default void init(String preset) {}

    String getName();
}
