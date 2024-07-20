package org.allaymc.api.registry;

import com.google.common.collect.BiMap;

/**
 * Allay Project 2023/10/28
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

    public void register(LEFT left, RIGHT right) {
        content.put(left, right);
    }
}
