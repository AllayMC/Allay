/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.allaymc.api.registry;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * {@code SimpleMappedRegistry} exposes key-based registration through the
 * general {@link Map} interface. The loader's map is initially shared without
 * copying; freezing replaces it with an unmodifiable shallow snapshot that
 * retains the current iteration order. Stored keys and values remain shared
 * objects after freezing.
 */
public class SimpleMappedRegistry<K, V> extends AbstractMappedRegistry<K, V, Map<K, V>> {
    /**
     * Loads the backing map immediately from the supplied input. The map is
     * retained directly until replacement or freezing, so mutations through
     * loader-held references initially affect registry lookups.
     */
    protected <I> SimpleMappedRegistry(I input, RegistryLoader<I, Map<K, V>> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Replaces current mappings with an unmodifiable shallow snapshot and
     * freezes registry mutations. Repeated calls retain the existing snapshot;
     * old map references become detached, but key and value objects remain
     * shared. Callers must coordinate external map writers while copying
     * occurs.
     */
    @Override
    public synchronized void freeze() {
        if (frozen) {
            return;
        }
        // Finish copying before publishing content and freeze state, so a failed
        // copy leaves the previous mappings and mutation policy in place.
        content = Collections.unmodifiableMap(new LinkedHashMap<>(content));
        super.freeze();
    }

    /**
     * Creates a registry by invoking the supplied loader with {@code null}
     * input. The loader must support that convention and return the map to
     * retain; loading occurs before this factory returns.
     */
    public static <I, K, V> SimpleMappedRegistry<K, V> create(RegistryLoader<I, Map<K, V>> registryLoader) {
        return new SimpleMappedRegistry<>(null, registryLoader);
    }

    /**
     * Loads a registry with {@code null} input, passes it to {@code setter},
     * then invokes {@code afterSet}. A {@code setter} failure skips
     * {@code afterSet}; an {@code afterSet} failure does not undo publication
     * by {@code setter}.
     */
    public static <I, K, V> void create(RegistryLoader<I, Map<K, V>> registryLoader, Consumer<SimpleMappedRegistry<K, V>> setter, Runnable afterSet) {
        var registry = new SimpleMappedRegistry<>(null, registryLoader);
        setter.accept(registry);
        afterSet.run();
    }

    /**
     * Creates a registry by loading its map from the supplied input. Loading
     * occurs immediately, and the returned map is retained directly without
     * copying until content replacement or freezing.
     */
    public static <I, K, V> SimpleMappedRegistry<K, V> create(I input, RegistryLoader<I, Map<K, V>> registryLoader) {
        return new SimpleMappedRegistry<>(input, registryLoader);
    }

    /**
     * Creates a registry using a loader obtained from the supplier once. The
     * loader receives {@code null} input immediately, so both supplier
     * invocation and loading finish before the registry is returned.
     */
    public static <I, K, V> SimpleMappedRegistry<K, V> create(Supplier<RegistryLoader<I, Map<K, V>>> registryLoader) {
        return new SimpleMappedRegistry<>(null, registryLoader.get());
    }

    /**
     * Creates a registry using {@code input} and a loader obtained once from
     * {@code registryLoader}. The loader runs immediately, and its map is
     * retained directly; later lookups do not invoke either callback again.
     */
    public static <I, K, V> SimpleMappedRegistry<K, V> create(I input, Supplier<RegistryLoader<I, Map<K, V>>> registryLoader) {
        return new SimpleMappedRegistry<>(input, registryLoader.get());
    }
}
