package cn.allay.api.block.property;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
@Deprecated
public interface BlockPropertyTypeRegistry extends MappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> {
    ApiInstanceHolder<BlockPropertyTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static BlockPropertyTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
