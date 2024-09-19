package org.allaymc.api.registry;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * A wrapper around a value which is loaded based on the output from the provided
 * {@link RegistryLoader}. This class is primarily designed to hold a registration
 * of some kind, however no limits are set on what it can hold, as long as the
 * specified RegistryLoader returns the same value type that is specified in the
 * generic.
 *
 * <p>
 * Below, a RegistryLoader is taken in the constructor. RegistryLoaders have two
 * generic types: the input, and the output. The input is what it takes in, whether
 * it be a string which references to a file, or nothing more than an integer. The
 * output is what it generates based on the input, and should be the same type as
 * the {@link CONTENT} generic specified in the registry.
 *
 * <p>
 * Registries can be very simple to create. Here is an example that simply parses a
 * number given a string:
 *
 * <pre>
 * {@code
 *     public static final SimpleRegistry<Integer> STRING_TO_INT = SimpleRegistry.create("5", Integer::parseInt);
 * }
 * </pre>
 *
 * <p>
 * This is a simple example which really wouldn't have much of a practical use,
 * however it demonstrates a fairly basic use case of how this system works. Typically
 * though, the first parameter would be a location of some sort, such as a file path
 * where the loader will load the mappings from.
 *
 * @param <CONTENT> the value being held by the registry
 *
 * @author GeyserMC | daoge_cmd
 */
@Getter
@Setter
public abstract class AbstractRegistry<CONTENT> implements Registry<CONTENT> {
    protected CONTENT content;

    protected <INPUT> AbstractRegistry(INPUT input, RegistryLoader<INPUT, CONTENT> registryLoader) {
        this.content = registryLoader.load(input);
    }

    @Override
    public void register(Consumer<CONTENT> consumer) {
        consumer.accept(content);
    }
}
