package org.allaymc.api.blockentity.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityTypeRegistry extends MappedRegistry<Identifier, BlockEntityType<?>, Map<Identifier, BlockEntityType<?>>> {
    ApiInstanceHolder<BlockEntityTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static BlockEntityTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
