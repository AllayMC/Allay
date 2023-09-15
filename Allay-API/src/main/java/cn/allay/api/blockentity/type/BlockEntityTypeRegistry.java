package cn.allay.api.blockentity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;

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
