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

package org.allaymc.server.registry.loader;

import lombok.experimental.UtilityClass;
import org.allaymc.api.registry.RegistryLoader;

import java.util.function.Supplier;

/**
 * {@code RegistryLoaders} supplies a shared resource NBT loader and small registry-loader
 * adapters for supplier-backed or initially absent values. The supplier adapter ignores its
 * input on every load, while the uninitialized adapter always yields {@code null} until its
 * owning registry installs another loader.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class RegistryLoaders {
    /**
     * {@code NBT} reads network-encoded compound tags from resource paths. Registries share this
     * stateless loader while supplying the resource name separately for each load operation.
     */
    public static final NbtRegistryLoader NBT = new NbtRegistryLoader();

    /**
     * Returns a loader that invokes {@code supplier} on every load and ignores its input. The
     * supplier is retained rather than evaluated now, so its later return values and failures
     * remain visible to the owning registry.
     */
    public static <V> RegistryLoader<Object, V> empty(Supplier<V> supplier) {
        return input -> supplier.get();
    }

    /**
     * Returns a loader that always produces {@code null}, regardless of input. Registries use
     * this placeholder before a real loader is installed; it does not allocate or retain an
     * empty registry value.
     */
    public static <I, V> RegistryLoader<I, V> uninitialized() {
        return input -> null;
    }
}