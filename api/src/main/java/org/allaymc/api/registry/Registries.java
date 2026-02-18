package org.allaymc.api.registry;

import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.creative.CreativeItemRegistry;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.fishing.FishingLoot;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pdc.PersistentDataTypeRegistry;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.feature.WorldFeature;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.storage.WorldStorage;

import java.nio.file.Path;
import java.util.UUID;
import java.util.function.Function;

/**
 * Registries holds all the common registries in allay.
 *
 * @author daoge_cmd
 */
public final class Registries {
    // Item
    public static DoubleKeyMappedRegistry<Integer, Identifier, EnchantmentType> ENCHANTMENTS;
    public static SimpleMappedRegistry<Identifier, ItemType<?>> ITEMS;
    public static SimpleMappedRegistry<ItemType<?>, Float> COMPOSTABLE_ITEMS;

    // Fishing
    public static SimpleMappedRegistry<Identifier, FishingLoot> FISHING_LOOTS;

    // Block Entity
    public static SimpleMappedRegistry<String, BlockEntityType<?>> BLOCK_ENTITIES;

    // Block
    public static SimpleMappedRegistry<Integer, BlockState> BLOCK_STATE_PALETTE;
    public static SimpleMappedRegistry<Identifier, BlockType<?>> BLOCKS;

    // Entity
    public static DoubleKeyMappedRegistry<Integer, Identifier, EffectType> EFFECTS;
    public static SimpleMappedRegistry<Identifier, EntityType<?>> ENTITIES;

    // Biome
    public static DoubleKeyMappedRegistry<Integer, Identifier, BiomeType> BIOMES;

    // World
    public static SimpleMappedRegistry<String, Function<Path, WorldStorage>> WORLD_STORAGE_FACTORIES;
    public static SimpleMappedRegistry<String, Function<String, WorldGenerator>> WORLD_GENERATOR_FACTORIES;
    public static SimpleMappedRegistry<Identifier, WorldFeature> WORLD_FEATURES;

    // POI (Point of Interest)
    public static SimpleMappedRegistry<BlockType<?>, PoiType> POI_TYPES;

    // Creative Item
    public static CreativeItemRegistry CREATIVE_ITEMS;

    // Recipe
    public static SimpleMappedRegistry<Identifier, Recipe> RECIPES;

    // Pack
    public static SimpleMappedRegistry<UUID, Pack> PACKS;

    // Command
    public static CommandRegistry COMMANDS;

    // Persistent Data Container (PDC)
    public static PersistentDataTypeRegistry PERSISTENT_DATA_TYPES;

    // Dispenser
    public static SimpleMappedRegistry<ItemType<?>, DispenseBehavior> DISPENSER_BEHAVIORS;
}
