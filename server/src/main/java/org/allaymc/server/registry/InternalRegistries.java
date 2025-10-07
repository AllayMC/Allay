package org.allaymc.server.registry;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.data.BlockTag;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.item.data.ItemData;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.data.TrimMaterial;
import org.allaymc.api.item.data.TrimPattern;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.block.property.BlockPropertyProcessor;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.type.ItemDefinition;
import org.allaymc.server.world.biome.BiomeId;

import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public final class InternalRegistries {
    public static SimpleMappedRegistry<ItemId, ItemData> ITEM_DATA;
    public static SimpleMappedRegistry<ItemId, Set<ItemTag>> ITEM_TAGS;

    public static SimpleMappedRegistry<BlockId, Map<Integer, BlockStateData>> BLOCK_STATE_DATA;
    public static SimpleMappedRegistry<BlockId, Set<BlockTag>> BLOCK_TAGS;
    public static SimpleMappedRegistry<BlockId, Integer> BLOCK_DEFAULT_STATE_HASHES;
    public static SimpleMappedRegistry<BlockPropertyType<?>, BlockPropertyProcessor> BLOCK_PROPERTY_PROCESSORS;

    public static SimpleMappedRegistry<ItemId, ItemDefinition> ITEM_DEFINITIONS;

    public static SimpleMappedRegistry<BiomeId, BiomeData> BIOME_DATA;

    public static SimpleMappedRegistry<String, TrimPattern> TRIM_PATTERNS;
    public static SimpleMappedRegistry<String, TrimMaterial> TRIM_MATERIALS;
}
