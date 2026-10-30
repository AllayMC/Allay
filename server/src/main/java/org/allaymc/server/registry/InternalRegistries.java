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
import org.allaymc.server.item.type.VanillaItemDefinitionData;
import org.allaymc.server.world.biome.BiomeId;

import java.util.Map;
import java.util.Set;

/**
 * {@code InternalRegistries} exposes resource-backed metadata used to construct
 * runtime types and encode client definitions. Bootstrap assigns these shared
 * registries before their consumers run; fields remain {@code null} until that
 * initialization step completes. Nested maps, sets, and data objects retain
 * their own mutability rules.
 *
 * <p>
 * {@code TRIM_PATTERNS} and {@code TRIM_MATERIALS} are frozen during protocol
 * initialization so later registry mutations cannot change the advertised trim
 * catalogs. Their values remain shared, and the public registry fields
 * themselves can still be reassigned independently of the frozen instances.
 *
 * <p>
 * Bootstrap loads {@code ITEM_DATA} and {@code ITEM_TAGS} before runtime item
 * types so their constructors and gameplay queries can resolve the same vanilla
 * identifiers.
 *
 * <p>
 * {@code BLOCK_STATE_DATA}, {@code BLOCK_TAGS},
 * {@code BLOCK_DEFAULT_STATE_HASHES}, and {@code BLOCK_PROPERTY_PROCESSORS}
 * connect each vanilla type with its state hashes, default state, tags, and
 * property processors used when constructing runtime types.
 *
 * <p>
 * Trim identifiers address the separate {@code TRIM_PATTERNS} and
 * {@code TRIM_MATERIALS} catalogs. Armor loading and protocol publishing read
 * the same registered definition objects from these two registries.
 *
 * @author daoge_cmd
 */
public final class InternalRegistries {
    /** Vanilla item properties indexed by generated item identifiers. */
    public static SimpleMappedRegistry<ItemId, ItemData> ITEM_DATA;
    /** Item tag sets indexed by generated item identifiers. */
    public static SimpleMappedRegistry<ItemId, Set<ItemTag>> ITEM_TAGS;

    /** Block metadata indexed first by type, then by state hash. */
    public static SimpleMappedRegistry<BlockId, Map<Integer, BlockStateData>> BLOCK_STATE_DATA;
    /** Block tag sets indexed by generated block identifiers. */
    public static SimpleMappedRegistry<BlockId, Set<BlockTag>> BLOCK_TAGS;
    /** Default state hashes indexed by generated block identifiers. */
    public static SimpleMappedRegistry<BlockId, Integer> BLOCK_DEFAULT_STATE_HASHES;
    /** Property processors indexed by their corresponding property definitions. */
    public static SimpleMappedRegistry<BlockPropertyType<?>, BlockPropertyProcessor> BLOCK_PROPERTY_PROCESSORS;

    /**
     * Vanilla component definitions indexed by generated item identifiers.
     * Vanilla protocol item definitions retain the component payload and item
     * version read from resources for use by version-specific packet encoders.
     */
    public static SimpleMappedRegistry<ItemId, VanillaItemDefinitionData> VANILLA_ITEM_DEFINITIONS;

    /**
     * Biome metadata indexed by generated biome identifiers. Biome type
     * construction uses these resource records for environmental properties
     * associated with each generated numeric and textual biome identity.
     */
    public static SimpleMappedRegistry<BiomeId, BiomeData> BIOME_DATA;

    /** Armor trim patterns indexed by serialized pattern IDs. */
    public static SimpleMappedRegistry<String, TrimPattern> TRIM_PATTERNS;
    /** Armor trim materials indexed by serialized material IDs. */
    public static SimpleMappedRegistry<String, TrimMaterial> TRIM_MATERIALS;
}
