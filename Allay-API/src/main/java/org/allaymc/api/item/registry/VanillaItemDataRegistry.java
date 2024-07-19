package org.allaymc.api.item.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.common.ItemData;
import org.allaymc.api.registry.AbstractMappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface VanillaItemDataRegistry extends AbstractMappedRegistry<VanillaItemId, ItemData, Map<VanillaItemId, ItemData>> {
    ApiInstanceHolder<VanillaItemDataRegistry> REGISTRY = ApiInstanceHolder.create();

    static VanillaItemDataRegistry getRegistry() {
        return REGISTRY.get();
    }
}
