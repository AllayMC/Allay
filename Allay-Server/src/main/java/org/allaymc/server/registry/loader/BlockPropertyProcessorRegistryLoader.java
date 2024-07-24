package org.allaymc.server.registry.loader;

import org.allaymc.api.block.property.BlockPropertyProcessor;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.registry.RegistryLoader;

import java.util.Map;

/**
 * Allay Project 2024/7/24
 *
 * @author daoge_cmd
 */
public class BlockPropertyProcessorRegistryLoader implements RegistryLoader<Void, Map<BlockPropertyType<?>, BlockPropertyProcessor>> {
    @Override
    public Map<BlockPropertyType<?>, BlockPropertyProcessor> load(Void $) {
        return Map.of();
    }
}
