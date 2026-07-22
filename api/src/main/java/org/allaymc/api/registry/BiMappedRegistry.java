package org.allaymc.api.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

/**
 * A registry which use {@link BiMap} as the content.
 *
 * @author daoge_cmd
 */
public class BiMappedRegistry<LEFT, RIGHT> extends AbstractRegistry<BiMap<LEFT, RIGHT>> {
    protected <INPUT> BiMappedRegistry(INPUT input, RegistryLoader<INPUT, BiMap<LEFT, RIGHT>> registryLoader) {
        super(input, registryLoader);
    }

    public RIGHT getByLeft(LEFT left) {
        return content.get(left);
    }

    public LEFT getByRight(RIGHT right) {
        return content.inverse().get(right);
    }

    public RIGHT getByLeftOrDefault(LEFT left, RIGHT defaultValue) {
        return content.getOrDefault(left, defaultValue);
    }

    public LEFT getByRightOrDefault(RIGHT right, LEFT defaultValue) {
        return content.inverse().getOrDefault(right, defaultValue);
    }

    public synchronized void register(LEFT left, RIGHT right) {
        ensureMutable();
        content.put(left, right);
    }

    /**
     * Freezes this registry using an immutable snapshot that preserves bidirectional lookup.
     */
    @Override
    public synchronized void freeze() {
        if (frozen) {
            return;
        }
        content = ImmutableBiMap.copyOf(content);
        super.freeze();
    }
}
