package org.allaymc.server.container;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

import java.util.*;

/**
 * {@code ContainerNetworkInfo} represents a container's window type, slot categories,
 * and reversible translation between network and internal slot indices. Window encoding
 * and stack-request handling use the same metadata to locate storage and report changed
 * slots, while holders use the held-category set to resolve aliases to container
 * identities.
 *
 * <p>
 * Some network layouts number slots differently from internal storage, so the builder
 * records explicit translations and fills uncovered internal destinations with identity
 * mappings. Explicit construction validates that no slot-category entry is {@code null}
 * and registers the record by type identity. The constructor retains {@code slotTypeTable}, {@code heldSlotTypes}, and
 * {@code networkSlotIndexMapper} directly. Callers share these mutable structures
 * with the record, and later supplier changes remain visible through its accessors.
 *
 * @param networkId numeric protocol window-type ID
 * @param type shared container identity token
 * @param slotTypeTable shared array mapping internal indices to network categories
 * @param heldSlotTypes shared category set used for holder and viewer lookup aliases
 * @param networkSlotIndexMapper shared bidirectional network-to-internal index map used
 *     for request and response translation
 *
 * @author daoge_cmd
 */
public record ContainerNetworkInfo(
        int networkId, ContainerType<?> type,
        ContainerSlotType[] slotTypeTable,
        Set<ContainerSlotType> heldSlotTypes,
        BiMap<Integer, Integer> networkSlotIndexMapper
) {

    /**
     * {@code TYPE_TO_INFO} stores metadata by container identity. Every explicit
     * constructor replaces the existing entry for its supplied type after table
     * validation.
     */
    private static final Map<ContainerType<?>, ContainerNetworkInfo> TYPE_TO_INFO = new HashMap<>();

    /**
     * The built-in layouts register shared container identities with their network
     * categories and reversible slot-index mappings during class initialization. Network
     * layout for the {@code CURSOR} container.
     */
    public static final ContainerNetworkInfo CURSOR = builder()
            .type(ContainerTypes.CURSOR)
            .mapAllSlotToType(ContainerSlotType.CURSOR)
            .build();

    /**
     * Network layout for the {@code ARMOR} container.
     */
    public static final ContainerNetworkInfo ARMOR = builder()
            .type(ContainerTypes.ARMOR)
            .mapAllSlotToType(ContainerSlotType.ARMOR)
            .build();

    /**
     * Network layout for the {@code OFFHAND} container.
     */
    public static final ContainerNetworkInfo OFFHAND = builder()
            .type(ContainerTypes.OFFHAND)
            .mapAllSlotToType(ContainerSlotType.OFFHAND)
            .mapNetworkSlotIndex(1, 0)
            .build();

    /**
     * Network layout for the {@code ENTITY_HAND} container.
     */
    public static final ContainerNetworkInfo ENTITY_HAND = builder()
            .type(ContainerTypes.ENTITY_HAND)
            .mapAllSlotToType(ContainerSlotType.INVENTORY)
            .build();

    /**
     * Network layout for the {@code PLAYER_INVENTORY} container.
     */
    public static final ContainerNetworkInfo PLAYER_INVENTORY = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.INVENTORY)
            .type(ContainerTypes.INVENTORY)
            .mapRangedSlotToType(0, 8, ContainerSlotType.HOTBAR)
            .mapRangedSlotToType(9, 35, ContainerSlotType.INVENTORY)
            .holdSlotType(ContainerSlotType.HOTBAR_AND_INVENTORY)
            .build();

    /**
     * Network layout for the {@code CREATED_OUTPUT} container.
     */
    public static final ContainerNetworkInfo CREATED_OUTPUT = builder()
            .type(ContainerTypes.CREATED_OUTPUT)
            .mapAllSlotToType(ContainerSlotType.CREATED_OUTPUT)
            .mapNetworkSlotIndex(50, 0)
            .build();

    /**
     * Network layout for the {@code BARREL} container.
     */
    public static final ContainerNetworkInfo BARREL = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.BARREL)
            .mapAllSlotToType(ContainerSlotType.BARREL)
            .build();

    /**
     * Network layout for the {@code CHEST} container.
     */
    public static final ContainerNetworkInfo CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * Network layout for the {@code HOPPER} container.
     */
    public static final ContainerNetworkInfo HOPPER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.HOPPER)
            .type(ContainerTypes.HOPPER)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * Network layout for the {@code CRAFTING_GRID} container.
     */
    public static final ContainerNetworkInfo CRAFTING_GRID = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.INVENTORY)
            .type(ContainerTypes.CRAFTING_GRID)
            .mapAllSlotToType(ContainerSlotType.CRAFTING_INPUT)
            .mapRangedNetworkSlotIndex(28, 31, 0)
            .build();

    /**
     * Network layout for the {@code CRAFTING_TABLE} container.
     */
    public static final ContainerNetworkInfo CRAFTING_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.WORKBENCH)
            .type(ContainerTypes.CRAFTING_TABLE)
            .mapAllSlotToType(ContainerSlotType.CRAFTING_INPUT)
            .mapRangedNetworkSlotIndex(32, 40, 0)
            .build();

    /**
     * Network layout for the {@code SHULKER_BOX} container.
     */
    public static final ContainerNetworkInfo SHULKER_BOX = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.SHULKER_BOX)
            .mapAllSlotToType(ContainerSlotType.SHULKER_BOX)
            .build();

    /**
     * Network layout for the {@code FURNACE} container.
     */
    public static final ContainerNetworkInfo FURNACE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.FURNACE)
            .type(ContainerTypes.FURNACE)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.FURNACE_INGREDIENT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .build();

    /**
     * Network layout for the {@code BLAST_FURNACE} container.
     */
    public static final ContainerNetworkInfo BLAST_FURNACE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.BLAST_FURNACE)
            .type(ContainerTypes.BLAST_FURNACE)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.BLAST_FURNACE_INGREDIENT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .build();

    /**
     * Network layout for the {@code SMOKER} container.
     */
    public static final ContainerNetworkInfo SMOKER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.SMOKER)
            .type(ContainerTypes.SMOKER)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.SMOKER_INGREDIENT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .build();

    /**
     * Network layout for the {@code ENCHANT_TABLE} container.
     */
    public static final ContainerNetworkInfo ENCHANT_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.ENCHANTMENT)
            .type(ContainerTypes.ENCHANT_TABLE)
            .mapSlotToType(EnchantTableContainer.INPUT_SLOT, ContainerSlotType.ENCHANTING_INPUT)
            .mapSlotToType(EnchantTableContainer.MATERIAL_SLOT, ContainerSlotType.ENCHANTING_MATERIAL)
            .mapRangedNetworkSlotIndex(14, 15, 0)
            .build();

    /**
     * Network layout for the {@code BEACON} container.
     */
    public static final ContainerNetworkInfo BEACON = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.BEACON)
            .type(ContainerTypes.BEACON)
            .mapAllSlotToType(ContainerSlotType.BEACON_PAYMENT)
            .mapNetworkSlotIndex(27, BeaconContainer.BEACON_PAYMENT_SLOT)
            .build();

    /**
     * Network layout for the {@code BREWING_STAND} container.
     */
    public static final ContainerNetworkInfo BREWING_STAND = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.BREWING_STAND)
            .type(ContainerTypes.BREWING_STAND)
            .mapSlotToType(BrewingStandContainer.REAGENT_SLOT, ContainerSlotType.BREWING_INPUT)
            .mapRangedSlotToType(1, 3, ContainerSlotType.BREWING_RESULT)
            .mapSlotToType(BrewingStandContainer.FUEL_SLOT, ContainerSlotType.BREWING_FUEL)
            .build();

    /**
     * Network layout for the {@code ENDER_CHEST} container.
     */
    public static final ContainerNetworkInfo ENDER_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.ENDER_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * Network layout for the {@code ANVIL} container.
     */
    public static final ContainerNetworkInfo ANVIL = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.ANVIL)
            .type(ContainerTypes.ANVIL)
            .mapSlotToType(AnvilContainer.INPUT_SLOT, ContainerSlotType.ANVIL_INPUT)
            .mapSlotToType(AnvilContainer.MATERIAL_SLOT, ContainerSlotType.ANVIL_MATERIAL)
            .mapSlotToType(AnvilContainer.RESULT_SLOT, ContainerSlotType.ANVIL_RESULT)
            .mapRangedNetworkSlotIndex(1, 3, 0)
            .build();

    /**
     * Network layout for the {@code STONECUTTER} container.
     */
    public static final ContainerNetworkInfo STONECUTTER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.STONECUTTER)
            .type(ContainerTypes.STONECUTTER)
            .mapSlotToType(StonecutterContainer.INPUT_SLOT, ContainerSlotType.STONECUTTER_INPUT)
            .mapSlotToType(StonecutterContainer.RESULT_SLOT, ContainerSlotType.STONECUTTER_RESULT)
            .mapRangedNetworkSlotIndex(3, 4, 0)
            .build();

    /**
     * Network layout for the {@code GRINDSTONE} container.
     */
    public static final ContainerNetworkInfo GRINDSTONE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.GRINDSTONE)
            .type(ContainerTypes.GRINDSTONE)
            .mapSlotToType(GrindstoneContainer.INPUT_SLOT, ContainerSlotType.GRINDSTONE_INPUT)
            .mapSlotToType(GrindstoneContainer.ADDITIONAL_SLOT, ContainerSlotType.GRINDSTONE_ADDITIONAL)
            .mapSlotToType(GrindstoneContainer.RESULT_SLOT, ContainerSlotType.GRINDSTONE_RESULT)
            .mapRangedNetworkSlotIndex(16, 18, 0)
            .build();

    /**
     * Network layout for the {@code CARTOGRAPHY_TABLE} container.
     */
    public static final ContainerNetworkInfo CARTOGRAPHY_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CARTOGRAPHY)
            .type(ContainerTypes.CARTOGRAPHY_TABLE)
            .mapSlotToType(CartographyTableContainer.INPUT_SLOT, ContainerSlotType.CARTOGRAPHY_INPUT)
            .mapSlotToType(CartographyTableContainer.ADDITIONAL_SLOT, ContainerSlotType.CARTOGRAPHY_ADDITIONAL)
            .mapSlotToType(CartographyTableContainer.RESULT_SLOT, ContainerSlotType.CARTOGRAPHY_RESULT)
            .mapRangedNetworkSlotIndex(12, 14, 0)
            .build();

    /**
     * Network layout for the {@code LOOM} container.
     */
    public static final ContainerNetworkInfo LOOM = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.LOOM)
            .type(ContainerTypes.LOOM)
            .mapSlotToType(LoomContainer.INPUT_SLOT, ContainerSlotType.LOOM_INPUT)
            .mapSlotToType(LoomContainer.DYE_SLOT, ContainerSlotType.LOOM_DYE)
            .mapSlotToType(LoomContainer.MATERIAL_SLOT, ContainerSlotType.LOOM_MATERIAL)
            .mapSlotToType(LoomContainer.RESULT_SLOT, ContainerSlotType.LOOM_RESULT)
            .mapRangedNetworkSlotIndex(9, 12, 0)
            .build();

    /**
     * Network layout for the {@code SMITHING_TABLE} container.
     */
    public static final ContainerNetworkInfo SMITHING_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.SMITHING_TABLE)
            .type(ContainerTypes.SMITHING_TABLE)
            .mapSlotToType(SmithingTableContainer.INPUT_SLOT, ContainerSlotType.SMITHING_TABLE_INPUT)
            .mapSlotToType(SmithingTableContainer.MATERIAL_SLOT, ContainerSlotType.SMITHING_TABLE_MATERIAL)
            .mapSlotToType(SmithingTableContainer.TEMPLATE_SLOT, ContainerSlotType.SMITHING_TABLE_TEMPLATE)
            .mapSlotToType(SmithingTableContainer.RESULT_SLOT, ContainerSlotType.SMITHING_TABLE_RESULT)
            .mapRangedNetworkSlotIndex(51, 54, 0)
            .build();

    /**
     * Network layout for the {@code DOUBLE_CHEST} container.
     */
    public static final ContainerNetworkInfo DOUBLE_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.DOUBLE_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * Network layout for the {@code DISPENSER} container.
     */
    public static final ContainerNetworkInfo DISPENSER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.DISPENSER)
            .type(ContainerTypes.DISPENSER)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * Network layout for the {@code DROPPER} container.
     */
    public static final ContainerNetworkInfo DROPPER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.DROPPER)
            .type(ContainerTypes.DROPPER)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * The fake layouts register synthetic chest identities with ordinary chest window
     * and slot categories for player interface synchronization. Network layout for the
     * {@code FAKE_CHEST} container.
     */
    public static final ContainerNetworkInfo FAKE_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.FAKE_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * Network layout for the {@code FAKE_DOUBLE_CHEST} container.
     */
    public static final ContainerNetworkInfo FAKE_DOUBLE_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.FAKE_DOUBLE_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    /**
     * Creates and registers metadata for one container identity. The slot-type table
     * must contain no {@code null} elements, and its supplied array, category set, and
     * index map are retained directly rather than copied. Successful construction
     * replaces the registry entry for {@code type}.
     */
    public ContainerNetworkInfo(int networkId, ContainerType<?> type, ContainerSlotType[] slotTypeTable, Set<ContainerSlotType> heldSlotTypes, BiMap<Integer, Integer> networkSlotIndexMapper) {
        this.networkId = networkId;
        this.type = type;
        this.slotTypeTable = slotTypeTable;
        for (var slotType : slotTypeTable) {
            if (slotType == null) {
                throw new IllegalArgumentException("Slot type table shouldn't contain null entry!");
            }
        }
        this.heldSlotTypes = heldSlotTypes;
        this.networkSlotIndexMapper = networkSlotIndexMapper;
        TYPE_TO_INFO.put(type, this);
    }

    /**
     * Returns the registered metadata for the supplied container identity. {@code null}
     * indicates that no entry exists; callers requiring network categories or slot
     * translation must handle that missing registration.
     */
    public static ContainerNetworkInfo getInfo(ContainerType<?> type) {
        return TYPE_TO_INFO.get(type);
    }

    /**
     * Returns a new builder for one container network layout.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Resolves the stored numeric window type through the protocol enum. The value must
     * identify a supported enum entry; an unknown or out-of-range ID fails in the
     * protocol lookup.
     */
    public org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType toNetworkType() {
        return org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.from(networkId);
    }

    /**
     * Returns the network slot category assigned to an internal slot index.
     */
    public ContainerSlotType getSlotType(int slot) {
        return slotTypeTable[slot];
    }

    /**
     * Returns the number of entries in the slot-type table.
     */
    public int size() {
        return slotTypeTable.length;
    }

    /**
     * {@code Builder} assembles container network categories and bidirectional slot
     * translations before registration. {@code type} allocates the slot table before
     * category assignment, and {@code build} retains the builder's mutable structures in
     * the resulting record.
     */
    public static class Builder {
        /**
         * {@code heldSlotTypes} accumulates categories from slot assignments and
         * explicit holdings. Built metadata retains this same set, so later builder
         * changes remain visible through the record.
         */
        private final Set<ContainerSlotType> heldSlotTypes = EnumSet.noneOf(ContainerSlotType.class);
        /**
         * {@code networkSlotIndexMapper} stores network-to-internal translations with
         * unique values. Built metadata retains this map, and its inverse supplies
         * internal-to-network lookups for response generation.
         */
        private final BiMap<Integer, Integer> networkSlotIndexMapper = HashBiMap.create();

        /**
         * {@code networkId} starts at {@code Integer.MIN_VALUE} for layouts without an
         * assigned window type. Callers must assign a supported ID before using {@code
         * toNetworkType()}.
         */
        private int networkId = Integer.MIN_VALUE;
        /**
         * Container identity selected for metadata registration.
         */
        private ContainerType<?> type;
        /**
         * {@code slotTypeTable} is allocated by {@code type} and must have every entry
         * assigned before construction. The record retains the resulting array directly.
         */
        private ContainerSlotType[] slotTypeTable;

        /**
         * Selects the numeric protocol window type and returns this builder. This
         * operation stores the supplied {@code id} without validation; {@code
         * toNetworkType()} later requires a supported protocol enum entry.
         */
        public Builder networkId(int id) {
            this.networkId = id;
            return this;
        }

        /**
         * Selects the supplied protocol enum's numeric window type and returns this
         * builder. The supplied {@code containerType} must be non-{@code null} because
         * this operation reads its numeric ID immediately.
         */
        public Builder networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType containerType) {
            this.networkId = containerType.getId();
            return this;
        }

        /**
         * Selects the container identity and allocates a fresh slot-category table of
         * its size. Existing held categories and index mappings remain, so callers must
         * account for those retained selections when reusing a builder.
         */
        public Builder type(ContainerType<?> type) {
            this.type = type;
            this.slotTypeTable = new ContainerSlotType[type.getSize()];
            return this;
        }

        /**
         * Assigns a network category to an inclusive internal slot range and records
         * that category as held. The container type must already be selected, and both
         * bounds must identify valid slots with {@code left <= right}.
         */
        public Builder mapRangedSlotToType(int left, int right, ContainerSlotType type) {
            if (slotTypeTable == null) {
                throw new IllegalStateException("The size must be set firstly!");
            }
            if (left > right) {
                throw new IllegalArgumentException("Left must smaller than right!");
            }
            if (left > slotTypeTable.length || right > slotTypeTable.length) {
                throw new IllegalArgumentException("Left or right bigger than size!");
            }

            heldSlotTypes.add(type);
            Arrays.fill(slotTypeTable, left, right + 1, type);
            return this;
        }

        /**
         * Assigns one network category to every internal slot and records it as held.
         * The container type must already be selected; a {@code null} category fails
         * immediately when the enum set attempts to record that category.
         */
        public Builder mapAllSlotToType(ContainerSlotType type) {
            if (slotTypeTable == null) {
                throw new IllegalStateException("The size must be set firstly!");
            }

            heldSlotTypes.add(type);
            Arrays.fill(slotTypeTable, type);
            return this;
        }

        /**
         * Assigns one internal slot's network category and records that category as
         * held. The container type must already be selected, and {@code slot} must
         * address its allocated table.
         */
        public Builder mapSlotToType(int slot, ContainerSlotType type) {
            if (slotTypeTable == null) {
                throw new IllegalStateException("The size must be set firstly!");
            }

            heldSlotTypes.add(type);
            slotTypeTable[slot] = type;
            return this;
        }

        /**
         * Adds a network category used to resolve this container without assigning that
         * category to an individual slot. The held set can therefore include aliases
         * alongside categories present in the slot table.
         */
        public Builder holdSlotType(ContainerSlotType type) {
            heldSlotTypes.add(type);
            return this;
        }

        /**
         * Maps one network index to an internal slot and returns this builder. The
         * bidirectional map requires unique internal values; assigning an internal slot
         * already owned by another network key can fail.
         */
        public Builder mapNetworkSlotIndex(int networkSlotIndex, int slot) {
            networkSlotIndexMapper.put(networkSlotIndex, slot);
            return this;
        }

        /**
         * Maps an inclusive network range to consecutive internal slots starting at
         * {@code slot}. Reversed bounds throw {@code IllegalArgumentException}; the
         * bidirectional map also rejects duplicate internal values while earlier range
         * entries may already be recorded.
         */
        public Builder mapRangedNetworkSlotIndex(int left, int right, int slot) {
            if (left > right) {
                throw new IllegalArgumentException("Left must smaller than right!");
            }

            for (int i = left, j = 0; i <= right; i++, j++) {
                networkSlotIndexMapper.put(i, slot + j);
            }
            return this;
        }

        /**
         * Completes unmapped internal slot destinations with identity entries and
         * registers the resulting metadata. The selected container type must provide an
         * allocated slot table, and all categories must be non-{@code null}.
         *
         * <p>
         * The inverse map identifies destinations already covered, but identity
         * insertion still uses the internal index as a network key. That key can replace
         * an explicit mapping already stored under the same key, so arbitrary
         * overlapping explicit mappings are not preserved in every case. The record
         * retains the builder's mutable table, category set, and index map rather than
         * copying them.
         */
        public ContainerNetworkInfo build() {
            var allaySlotToNetworkSlot = networkSlotIndexMapper.inverse();
            // Fill uncovered internal destinations through identity entries. A reused
            // network key can still replace an explicit mapping, because the inverse
            // check protects values rather than keys.
            for (int i = 0; i < slotTypeTable.length; i++) {
                if (!allaySlotToNetworkSlot.containsKey(i)) {
                    networkSlotIndexMapper.put(i, i);
                }
            }
            return new ContainerNetworkInfo(networkId, type, slotTypeTable, heldSlotTypes, networkSlotIndexMapper);
        }
    }
}
