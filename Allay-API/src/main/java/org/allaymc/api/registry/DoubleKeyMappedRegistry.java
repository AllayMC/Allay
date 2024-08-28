package org.allaymc.api.registry;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class DoubleKeyMappedRegistry<K1, K2, VALUE> extends AbstractRegistry<DoubleKeyMappedRegistry.MapPair<K1, K2, VALUE>> {
    protected <INPUT> DoubleKeyMappedRegistry(INPUT input, RegistryLoader<INPUT, MapPair<K1, K2, VALUE>> registryLoader) {
        super(input, registryLoader);
    }

    public static <I, K1, K2, VALUE> void create(RegistryLoader<I, MapPair<K1, K2, VALUE>> registryLoader, Consumer<DoubleKeyMappedRegistry<K1, K2, VALUE>> setter, Runnable afterSet) {
        var registry = new DoubleKeyMappedRegistry<>(null, registryLoader);
        setter.accept(registry);
        afterSet.run();
    }

    public VALUE getByK1(K1 k1) {
        return content.m1.get(k1);
    }

    public VALUE getByK2(K2 k2) {
        return content.m2.get(k2);
    }

    public VALUE getByK1OrDefault(K1 k1, VALUE defaultValue) {
        return content.m1.getOrDefault(k1, defaultValue);
    }

    public VALUE getByK2OrDefault(K2 k2, VALUE defaultValue) {
        return content.m2.getOrDefault(k2, defaultValue);
    }

    public void register(K1 k1, K2 k2, VALUE value) {
        content.m1.put(k1, value);
        content.m2.put(k2, value);
    }

    public record MapPair<K1, K2, VALUE>(Map<K1, VALUE> m1, Map<K2, VALUE> m2) {}
}
