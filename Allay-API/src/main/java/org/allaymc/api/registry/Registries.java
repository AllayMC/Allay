package org.allaymc.api.registry;

import org.allaymc.api.block.component.common.BlockStateData;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.material.MaterialType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.List;
import java.util.Map;

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
    // Block
    public static SimpleMappedRegistry<MaterialType, Material> MATERIALS;
    public static IntMappedRegistry<BlockState> BLOCK_STATE_PALETTE;
    public static SimpleMappedRegistry<VanillaBlockId, Map<Integer, BlockStateData>> BLOCK_STATE_DATA;
    public static SimpleMappedRegistry<Identifier, BlockType<?>> BLOCK_TYPES;
    public static SimpleRegistry<List<BlockDefinition>> BLOCK_DEFINITIONS;
}
