package org.allaymc.api.registry;

import org.allaymc.api.block.component.common.BlockStateData;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.material.MaterialType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.common.ItemData;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.storage.WorldStorage;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Holds all the common registries in allay.
 *
 * <p>
 * Allay Project 2024/7/19
 *
 * @author daoge_cmd
 */
public final class Registries {
    // Item
    public static DoubleKeyMappedRegistry<Integer, Identifier, EnchantmentType> ENCHANTMENTS;
    public static SimpleMappedRegistry<VanillaItemId, ItemData> VANILLA_ITEM_DATA;
    public static SimpleMappedRegistry<Identifier, ItemType<?>> ITEM_TYPES;
    /**
     * The custom block/item/entity etc. are not supported currently <p>
     * And we have no plan to implement them in the near future <p>
     * So we can cache the definitions to speed up player joining
     */
    public static SimpleRegistry<List<ItemDefinition>> ITEM_DEFINITIONS;

    // Block
    public static SimpleMappedRegistry<MaterialType, Material> MATERIALS;
    public static IntMappedRegistry<BlockState> BLOCK_STATE_PALETTE;
    public static SimpleMappedRegistry<VanillaBlockId, Map<Integer, BlockStateData>> VANILLA_BLOCK_STATE_DATA;
    public static SimpleMappedRegistry<Identifier, BlockType<?>> BLOCK_TYPES;
    public static SimpleRegistry<List<BlockDefinition>> BLOCK_DEFINITIONS;

    // World
    public static SimpleMappedRegistry<String, Function<Path, WorldStorage>> WORLD_STORAGE_FACTORIES;
    public static SimpleMappedRegistry<String, Function<String, WorldGenerator>> WORLD_GENERATOR_FACTORIES;

    // Creative Item Registry
    public static IntMappedRegistry<ItemStack> CREATIVE_ITEMS;
    public static SimpleRegistry<org.cloudburstmc.protocol.bedrock.data.inventory.ItemData[]> CREATIVE_ITEM_NETWORK_CONTENT;
}
