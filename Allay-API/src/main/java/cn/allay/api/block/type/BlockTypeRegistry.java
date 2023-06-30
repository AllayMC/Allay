package cn.allay.api.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd
 */
public interface BlockTypeRegistry extends MappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> {
    ApiInstanceHolder<BlockTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static BlockTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
