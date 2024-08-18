package org.allaymc.api.registry;

import org.allaymc.api.block.BlockStateData;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.material.MaterialType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemData;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.NetworkRecipe;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.storage.WorldStorage;

import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
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
    public static SimpleMappedRegistry<Identifier, ItemType<?>> ITEMS;

    // BlockEntity
    public static SimpleMappedRegistry<String, BlockEntityType<?>> BLOCK_ENTITIES;

    // Block
    public static SimpleMappedRegistry<MaterialType, Material> MATERIALS;
    public static IntMappedRegistry<BlockState> BLOCK_STATE_PALETTE;
    public static SimpleMappedRegistry<VanillaBlockId, Map<Integer, BlockStateData>> VANILLA_BLOCK_STATE_DATA;
    public static SimpleMappedRegistry<Identifier, BlockType<?>> BLOCKS;

    // Entity
    public static DoubleKeyMappedRegistry<Integer, Identifier, EffectType> EFFECTS;
    public static SimpleMappedRegistry<Identifier, EntityType<?>> ENTITIES;

    // World
    public static SimpleMappedRegistry<String, Function<Path, WorldStorage>> WORLD_STORAGE_FACTORIES;
    public static SimpleMappedRegistry<String, Function<String, WorldGenerator>> WORLD_GENERATOR_FACTORIES;

    // Creative Item
    public static IntMappedRegistry<ItemStack> CREATIVE_ITEMS;

    // Recipe
    public static IntMappedRegistry<NetworkRecipe> RECIPES;
    public static SimpleMappedRegistry<Identifier, FurnaceRecipe> FURNACE_RECIPES;
    // TODO: PotionMixData
    // TODO: ContainerMixData
    // TODO: MaterialReducer

    // Pack
    public static SimpleMappedRegistry<UUID, Pack> PACKS;

    // Command
    public static CommandRegistry COMMANDS;
}
