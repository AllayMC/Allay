package cn.allay.api.block.component.impl.attribute;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public interface VanillaBlockAttributeRegistry extends MappedRegistry<VanillaBlockId, Map<Integer, BlockAttributes>, Map<VanillaBlockId, Map<Integer, BlockAttributes>>> {
    ApiInstanceHolder<VanillaBlockAttributeRegistry> REGISTRY = ApiInstanceHolder.of();

    static VanillaBlockAttributeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
