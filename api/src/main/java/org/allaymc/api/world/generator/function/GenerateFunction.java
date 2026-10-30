package org.allaymc.api.world.generator.function;

/**
 * {@code GenerateFunction} defines preset initialization shared by all
 * world-generation functions. A world generator initializes each configured
 * function before dimension assignment and before invoking its stage callbacks;
 * the default implementation accepts the preset without retaining configuration
 * or performing work.
 *
 * @author daoge_cmd
 */
public interface GenerateFunction {
    /**
     * Initializes this function from the generator's preset before stage
     * execution begins. The default implementation does nothing; custom
     * functions define their format and must account for reuse across generator
     * builds.
     */
    default void init(String preset) {
    }
}
