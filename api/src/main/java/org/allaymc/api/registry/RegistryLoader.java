package org.allaymc.api.registry;

/**
 * {@code RegistryLoader} produces registry content from an
 * implementation-defined input value. Registry factories invoke a loader during
 * construction, including with {@code null} when no explicit input is supplied.
 * Each implementation defines supported inputs, resource handling, and the
 * ownership of its returned content.
 *
 * @param <INPUT> the input to load the registry from
 * @param <OUTPUT> the output of the registry
 *
 * @author GeyserMC | daoge_cmd
 */
@FunctionalInterface
public interface RegistryLoader<INPUT, OUTPUT> {
    /**
     * Loads content for a registry from the supplied input. Factories without
     * an explicit input pass {@code null}; a loader used with those factories
     * must accept that convention rather than requiring an input value.
     */
    OUTPUT load(INPUT input);
}
