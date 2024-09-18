package org.allaymc.api.registry;

/**
 * Represents a registry loader. {@link INPUT} is the input value, which can be anything,
 * but is commonly a file path or something similar. {@link OUTPUT} represents the output
 * type returned by this, which can also be anything.
 * <p>
 *
 * @param <INPUT>  the input to load the registry from
 * @param <OUTPUT> the output of the registry
 *
 * @author GeyserMC | daoge_cmd
 */
@FunctionalInterface
public interface RegistryLoader<INPUT, OUTPUT> {
    /**
     * Loads an output from the given input.
     *
     * @param input the input
     *
     * @return the output
     */
    OUTPUT load(INPUT input);
}
