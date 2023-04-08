package cn.allay.block.property;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public interface BlockPropertyTypeRegistry extends MappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> {
    ApiInstanceHolder<BlockPropertyTypeRegistry> REGISTRY = ApiInstanceHolder.create();

    static BlockPropertyTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
