package cn.allay.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.identifier.Identifier;
import cn.allay.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
public interface BlockTypeRegistry extends MappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> {

    ApiInstanceHolder<BlockTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static BlockTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
