package org.allaymc.server.registry;

import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemData;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.server.item.type.ItemComponentData;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public final class InternalRegistries {
    public static SimpleMappedRegistry<ItemId, ItemData> ITEM_DATA;

    public static SimpleMappedRegistry<BlockId, Map<Integer, BlockStateData>> BLOCK_STATE_DATA;

    public static SimpleMappedRegistry<ItemId, ItemComponentData> ITEM_COMPONENT_DATA;
}
