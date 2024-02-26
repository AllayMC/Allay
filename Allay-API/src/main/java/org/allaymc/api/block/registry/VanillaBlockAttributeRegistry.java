package org.allaymc.api.block.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.component.common.BlockAttributes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface VanillaBlockAttributeRegistry extends MappedRegistry<VanillaBlockId, Map<Integer, BlockAttributes>, Map<VanillaBlockId, Map<Integer, BlockAttributes>>> {
    ApiInstanceHolder<VanillaBlockAttributeRegistry> REGISTRY = ApiInstanceHolder.create();

    static VanillaBlockAttributeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
