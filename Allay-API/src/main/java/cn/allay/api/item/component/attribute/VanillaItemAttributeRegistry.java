package cn.allay.api.item.component.attribute;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface VanillaItemAttributeRegistry extends MappedRegistry<VanillaItemId, ItemAttributes, Map<VanillaItemId, ItemAttributes>> {
    ApiInstanceHolder<VanillaItemAttributeRegistry> REGISTRY = ApiInstanceHolder.of();

    static VanillaItemAttributeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
