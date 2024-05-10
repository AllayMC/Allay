package org.allaymc.api.block.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.registry.MappedRegistry;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd
 */
public interface BlockTypeRegistry extends MappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> {
    ApiInstanceHolder<BlockTypeRegistry> REGISTRY = ApiInstanceHolder.create();

    static BlockTypeRegistry getRegistry() {
        return REGISTRY.get();
    }

    List<BlockDefinition> getBlockDefinitions();
}
