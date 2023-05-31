package cn.allay.api.item.component.impl.attribute;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public interface VanillaItemAttributeRegistry extends MappedRegistry<VanillaItemId, ItemAttributes, Map<VanillaItemId, ItemAttributes>> {
    ApiInstanceHolder<VanillaItemAttributeRegistry> REGISTRY = ApiInstanceHolder.of();

    static VanillaItemAttributeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
