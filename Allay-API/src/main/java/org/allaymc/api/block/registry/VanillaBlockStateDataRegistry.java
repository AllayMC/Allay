package org.allaymc.api.block.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.component.common.BlockStateData;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface VanillaBlockStateDataRegistry extends MappedRegistry<VanillaBlockId, Map<Integer, BlockStateData>, Map<VanillaBlockId, Map<Integer, BlockStateData>>> {
    ApiInstanceHolder<VanillaBlockStateDataRegistry> REGISTRY = ApiInstanceHolder.create();

    static VanillaBlockStateDataRegistry getRegistry() {
        return REGISTRY.get();
    }
}
