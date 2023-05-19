package cn.allay.item.component.impl.attribute;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.item.data.VanillaItemId;
import cn.allay.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public interface VanillaItemAttributeRegistry extends MappedRegistry<VanillaItemId, ItemAttributes, Map<VanillaItemId, ItemAttributes>> {
    ApiInstanceHolder<VanillaItemAttributeRegistry> REGISTRY = ApiInstanceHolder.create();

    static VanillaItemAttributeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
