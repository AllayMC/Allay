package cn.allay.api.registry;

/**
 * Represents a registry loader. {@link I} is the input value, which can be anything,
 * but is commonly a file path or something similar. {@link O} represents the output
 * type returned by this, which can also be anything.
 * <p>
 * @author GeyserMC | daoge_cmd <br>
 * @date 2023/3/18 <br>
 * Allay Project <br>
 *
 * @param <I> the input to load the registry from
 * @param <O> the output of the registry
 */
@FunctionalInterface
public interface RegistryLoader<I, O> {

    /**
     * Loads an output from the given input.
     *
     * @param input the input
     * @return the output
     */
    O load(I input);
}
