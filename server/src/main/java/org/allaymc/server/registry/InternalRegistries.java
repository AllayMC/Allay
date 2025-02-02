package org.allaymc.server.registry;

import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.item.component.data.ItemData;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.registry.SimpleMappedRegistry;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public final class InternalRegistries {
    // NOTICE: Only vanilla item data is stored in the item data registry.
    public static SimpleMappedRegistry<ItemId, ItemData> ITEM_DATA;
    // NOTICE: Only vanilla block state data is stored in the block state data registry.
    public static SimpleMappedRegistry<BlockId, Map<Integer, BlockStateData>> BLOCK_STATE_DATA;
}
