package org.allaymc.server.registry;

import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.registry.SimpleMappedRegistry;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public final class InternalRegistries {
    public static SimpleMappedRegistry<BlockId, Map<Integer, BlockStateData>> BLOCK_STATE_DATA;
}
