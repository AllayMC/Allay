package org.allaymc.api.item.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.registry.AbstractMappedRegistry;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

import java.util.Map;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public interface CreativeItemRegistry extends AbstractMappedRegistry<Integer, ItemStack, Map<Integer, ItemStack>> {
    ApiInstanceHolder<CreativeItemRegistry> REGISTRY = ApiInstanceHolder.create();

    static CreativeItemRegistry getRegistry() {
        return REGISTRY.get();
    }

    ItemData[] getNetworkItemDataArray();

    void rebuildNetworkItemDataArray();
}
