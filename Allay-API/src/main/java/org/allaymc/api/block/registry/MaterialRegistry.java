package org.allaymc.api.block.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.material.MaterialType;
import org.allaymc.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2024/7/2
 *
 * @author daoge_cmd
 */
public interface MaterialRegistry extends MappedRegistry<MaterialType, Material, Map<MaterialType, Material>> {
    ApiInstanceHolder<MaterialRegistry> REGISTRY = ApiInstanceHolder.create();

    static MaterialRegistry getRegistry() {
        return REGISTRY.get();
    }
}
