package org.allaymc.server.registry;

import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemData;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.registry.SimpleRegistry;
import org.allaymc.server.block.property.BlockPropertyProcessor;
import org.allaymc.server.item.type.ItemComponentData;
import org.cloudburstmc.protocol.bedrock.data.TrimMaterial;
import org.cloudburstmc.protocol.bedrock.data.TrimPattern;

import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public final class InternalRegistries {
    public static SimpleMappedRegistry<ItemId, ItemData> ITEM_DATA;

    public static SimpleMappedRegistry<BlockId, Map<Integer, BlockStateData>> BLOCK_STATE_DATA;
    public static SimpleMappedRegistry<BlockPropertyType<?>, BlockPropertyProcessor> BLOCK_PROPERTY_PROCESSORS;

    public static SimpleMappedRegistry<ItemId, ItemComponentData> ITEM_COMPONENT_DATA;

    public static SimpleRegistry<Set<TrimPattern>> TRIM_PATTERNS;
    public static SimpleRegistry<Set<TrimMaterial>> TRIM_MATERIALS;
}
