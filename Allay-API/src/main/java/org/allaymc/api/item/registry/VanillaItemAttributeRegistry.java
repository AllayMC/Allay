package org.allaymc.api.item.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.common.ItemAttributes;
import org.allaymc.api.registry.MappedRegistry;

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
