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
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.feature.WorldFeature;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.storage.WorldStorage;

import java.nio.file.Path;
import java.util.UUID;
import java.util.function.Function;

/**
 * {@code Registries} exposes the registries populated during server bootstrap
 * for plugin and runtime use. Its fields start as {@code null} and are assigned
 * in dependency order; consumers must wait for initialization of the registry
 * they need. The public fields hold shared registry instances rather than
 * per-caller copies.
 *
 * <p>
 * Protocol initialization freezes {@code ITEMS}, {@code BLOCKS},
 * {@code BLOCK_STATE_PALETTE}, {@code RECIPES}, {@code ENTITIES},
 * {@code BIOMES}, {@code DIMENSIONS}, {@code PACKS}, and {@code CREATIVE_ITEMS}
 * before clients use their definitions. Other registries keep their own
 * mutation policies. Freezing an instance does not prevent reassignment of
 * these public fields or imply deep immutability of the values stored inside
 * it.
 *
 * <p>
 * {@code ENCHANTMENTS}, {@code ITEMS}, and {@code COMPOSTABLE_ITEMS} connect
 * item identifiers to types and the enchantment and composting rules consulted
 * when those types are used during gameplay.
 *
 * <p>
 * {@code BLOCKS} describes block type behavior, while
 * {@code BLOCK_STATE_PALETTE} resolves concrete state hashes used to recover a
 * block's property combination and associated type.
 *
 * <p>
 * Entity creation and effect application use {@code ENTITIES} and
 * {@code EFFECTS} to resolve identifiers into the registered runtime
 * definitions they represent.
 *
 * <p>
 * World setup selects {@code WORLD_STORAGE_FACTORIES} and
 * {@code WORLD_GENERATOR_FACTORIES} by configuration names. Feature placement
 * resolves named operations from {@code WORLD_FEATURES} during world
 * generation.
 *
 * @author daoge_cmd
 */
public final class Registries {
    /** Enchantment types indexed by numeric ID and resource identifier. */
    public static DoubleKeyMappedRegistry<Integer, Identifier, EnchantmentType> ENCHANTMENTS;
    /** Item types indexed by their resource identifiers. */
    public static SimpleMappedRegistry<Identifier, ItemType<?>> ITEMS;
    /** Composting success probabilities, from zero to one, indexed by item type. */
    public static SimpleMappedRegistry<ItemType<?>, Float> COMPOSTABLE_ITEMS;

    /**
     * Fishing loot definitions indexed by resource identifier. Fishing tables
     * enumerate these loot definitions when calculating the possible catches
     * associated with a fishing attempt and its conditions.
     */
    public static SimpleMappedRegistry<Identifier, FishingLoot> FISHING_LOOTS;

    /**
     * Block entity types indexed by their serialized names. Block entity
     * loading resolves saved type names through this registry before
     * constructing the appropriate persistent block entity instance.
     */
    public static SimpleMappedRegistry<String, BlockEntityType<?>> BLOCK_ENTITIES;

    /** Concrete block states indexed by their state hashes. */
    public static SimpleMappedRegistry<Integer, BlockState> BLOCK_STATE_PALETTE;
    /** Block types indexed by their resource identifiers. */
    public static SimpleMappedRegistry<Identifier, BlockType<?>> BLOCKS;

    /** Effect types indexed by numeric ID and resource identifier. */
    public static DoubleKeyMappedRegistry<Integer, Identifier, EffectType> EFFECTS;
    /** Entity types indexed by their resource identifiers. */
    public static SimpleMappedRegistry<Identifier, EntityType<?>> ENTITIES;

    /**
     * Biome types indexed by numeric ID and resource identifier. Biome
     * definitions support numeric lookups for stored terrain data and
     * identifier lookups for callers working with named biome types.
     */
    public static DoubleKeyMappedRegistry<Integer, Identifier, BiomeType> BIOMES;

    /**
     * Dimension types indexed by numeric ID and resource identifier. Dimension
     * definitions connect configured dimension identities with their height and
     * environment rules before worlds construct their dimension instances.
     */
    public static DoubleKeyMappedRegistry<Integer, Identifier, DimensionType> DIMENSIONS;

    /** Storage factories accepting the world's filesystem directory. */
    public static SimpleMappedRegistry<String, Function<Path, WorldStorage>> WORLD_STORAGE_FACTORIES;
    /** Generator factories accepting a generator-specific preset string. */
    public static SimpleMappedRegistry<String, Function<String, WorldGenerator>> WORLD_GENERATOR_FACTORIES;
    /** World feature implementations indexed by resource identifier. */
    public static SimpleMappedRegistry<Identifier, WorldFeature> WORLD_FEATURES;

    /**
     * Point-of-interest types associated with matching block types. Point-of-
     * interest updates use the block type to select the definition describing
     * which persistent point of interest that block contributes.
     */
    public static SimpleMappedRegistry<BlockType<?>, PoiType> POI_TYPES;

    /**
     * {@code CREATIVE_ITEMS} holds shared creative inventory entries and
     * display groups. These entries are maintained separately from item types
     * because multiple displayed stacks can refer to one registered item type.
     */
    public static CreativeItemRegistry CREATIVE_ITEMS;

    /**
     * Crafting recipes indexed by their resource identifiers. Crafting and
     * protocol recipe tables consult the same definitions so recipe identifiers
     * resolve to the registered server-side recipe objects.
     */
    public static SimpleMappedRegistry<Identifier, Recipe> RECIPES;

    /**
     * Resource packs indexed by their manifest UUIDs. Resource-pack negotiation
     * enumerates these registered packs and uses their {@code UUID} keys to
     * identify the content offered to connecting clients.
     */
    public static SimpleMappedRegistry<UUID, Pack> PACKS;

    /**
     * {@code COMMANDS} holds shared command names, aliases, and registered
     * handlers. Command execution and command metadata publishing share this
     * registry so registered command names refer to the same command
     * implementations.
     */
    public static CommandRegistry COMMANDS;

    /**
     * Persistent data adapters indexed by their supported data types.
     * Persistent data containers use registered adapters to translate custom
     * values into supported primitive representations and restore them when
     * requested.
     */
    public static PersistentDataTypeRegistry PERSISTENT_DATA_TYPES;

    /**
     * Dispensing behaviors associated with matching item types. Dispensers
     * select item-specific behavior from this registry before applying their
     * fallback behavior for items without an explicit registration.
     */
    public static SimpleMappedRegistry<ItemType<?>, DispenseBehavior> DISPENSER_BEHAVIORS;
}
