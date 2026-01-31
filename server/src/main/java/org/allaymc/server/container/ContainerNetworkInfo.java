package org.allaymc.server.container;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

import java.util.*;

/**
 * @author daoge_cmd
 */
public record ContainerNetworkInfo(
        int networkId, ContainerType<?> type,
        ContainerSlotType[] slotTypeTable,
        Set<ContainerSlotType> heldSlotTypes,
        // The slot range provided by vanilla for some containers does not match the design of Allay.
        // For example, crafting table: 32-39 in vanilla but 0-8 in Allay
        // This bidirectional mapping table is used to resolve these issues
        // network slot <-> slot that Allay used
        BiMap<Integer, Integer> networkSlotIndexMapper
) {

    private static final Map<ContainerType<?>, ContainerNetworkInfo> TYPE_TO_INFO = new HashMap<>();

    public static final ContainerNetworkInfo CURSOR = builder()
            .type(ContainerTypes.CURSOR)
            .mapAllSlotToType(ContainerSlotType.CURSOR)
            .build();

    public static final ContainerNetworkInfo ARMOR = builder()
            .type(ContainerTypes.ARMOR)
            .mapAllSlotToType(ContainerSlotType.ARMOR)
            .build();

    public static final ContainerNetworkInfo OFFHAND = builder()
            .type(ContainerTypes.OFFHAND)
            .mapAllSlotToType(ContainerSlotType.OFFHAND)
            .mapNetworkSlotIndex(1, 0)
            .build();

    public static final ContainerNetworkInfo ARMOR_STAND_HAND = builder()
            .type(ContainerTypes.ARMOR_STAND_HAND)
            .mapAllSlotToType(ContainerSlotType.INVENTORY)
            .build();

    public static final ContainerNetworkInfo PLAYER_INVENTORY = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.INVENTORY)
            .type(ContainerTypes.INVENTORY)
            .mapRangedSlotToType(0, 8, ContainerSlotType.HOTBAR)
            .mapRangedSlotToType(9, 35, ContainerSlotType.INVENTORY)
            .holdSlotType(ContainerSlotType.HOTBAR_AND_INVENTORY)
            .build();

    public static final ContainerNetworkInfo CREATED_OUTPUT = builder()
            .type(ContainerTypes.CREATED_OUTPUT)
            .mapAllSlotToType(ContainerSlotType.CREATED_OUTPUT)
            .mapNetworkSlotIndex(50, 0)
            .build();

    public static final ContainerNetworkInfo BARREL = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.BARREL)
            .mapAllSlotToType(ContainerSlotType.BARREL)
            .build();

    public static final ContainerNetworkInfo CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public static final ContainerNetworkInfo HOPPER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.HOPPER)
            .type(ContainerTypes.HOPPER)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public static final ContainerNetworkInfo CRAFTING_GRID = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.INVENTORY)
            .type(ContainerTypes.CRAFTING_GRID)
            .mapAllSlotToType(ContainerSlotType.CRAFTING_INPUT)
            .mapRangedNetworkSlotIndex(28, 31, 0)
            .build();

    public static final ContainerNetworkInfo CRAFTING_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.WORKBENCH)
            .type(ContainerTypes.CRAFTING_TABLE)
            .mapAllSlotToType(ContainerSlotType.CRAFTING_INPUT)
            .mapRangedNetworkSlotIndex(32, 40, 0)
            .build();

    public static final ContainerNetworkInfo SHULKER_BOX = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.SHULKER_BOX)
            .mapAllSlotToType(ContainerSlotType.SHULKER_BOX)
            .build();

    public static final ContainerNetworkInfo FURNACE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.FURNACE)
            .type(ContainerTypes.FURNACE)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.FURNACE_INGREDIENT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .build();

    public static final ContainerNetworkInfo BLAST_FURNACE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.BLAST_FURNACE)
            .type(ContainerTypes.BLAST_FURNACE)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.BLAST_FURNACE_INGREDIENT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .build();

    public static final ContainerNetworkInfo SMOKER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.SMOKER)
            .type(ContainerTypes.SMOKER)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.SMOKER_INGREDIENT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .build();

    public static final ContainerNetworkInfo ENCHANT_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.ENCHANTMENT)
            .type(ContainerTypes.ENCHANT_TABLE)
            .mapSlotToType(EnchantTableContainer.INPUT_SLOT, ContainerSlotType.ENCHANTING_INPUT)
            .mapSlotToType(EnchantTableContainer.MATERIAL_SLOT, ContainerSlotType.ENCHANTING_MATERIAL)
            .mapRangedNetworkSlotIndex(14, 15, 0)
            .build();

    public static final ContainerNetworkInfo BEACON = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.BEACON)
            .type(ContainerTypes.BEACON)
            .mapAllSlotToType(ContainerSlotType.BEACON_PAYMENT)
            .mapNetworkSlotIndex(27, BeaconContainer.BEACON_PAYMENT_SLOT)
            .build();

    public static final ContainerNetworkInfo BREWING_STAND = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.BREWING_STAND)
            .type(ContainerTypes.BREWING_STAND)
            .mapSlotToType(BrewingStandContainer.REAGENT_SLOT, ContainerSlotType.BREWING_INPUT)
            .mapRangedSlotToType(1, 3, ContainerSlotType.BREWING_RESULT)
            .mapSlotToType(BrewingStandContainer.FUEL_SLOT, ContainerSlotType.BREWING_FUEL)
            .build();

    public static final ContainerNetworkInfo ENDER_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.ENDER_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public static final ContainerNetworkInfo ANVIL = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.ANVIL)
            .type(ContainerTypes.ANVIL)
            .mapSlotToType(AnvilContainer.INPUT_SLOT, ContainerSlotType.ANVIL_INPUT)
            .mapSlotToType(AnvilContainer.MATERIAL_SLOT, ContainerSlotType.ANVIL_MATERIAL)
            .mapSlotToType(AnvilContainer.RESULT_SLOT, ContainerSlotType.ANVIL_RESULT)
            .mapRangedNetworkSlotIndex(1, 3, 0)
            .build();

    public static final ContainerNetworkInfo STONECUTTER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.STONECUTTER)
            .type(ContainerTypes.STONECUTTER)
            .mapSlotToType(StonecutterContainer.INPUT_SLOT, ContainerSlotType.STONECUTTER_INPUT)
            .mapSlotToType(StonecutterContainer.RESULT_SLOT, ContainerSlotType.STONECUTTER_RESULT)
            .mapRangedNetworkSlotIndex(3, 4, 0)
            .build();

    public static final ContainerNetworkInfo GRINDSTONE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.GRINDSTONE)
            .type(ContainerTypes.GRINDSTONE)
            .mapSlotToType(GrindstoneContainer.INPUT_SLOT, ContainerSlotType.GRINDSTONE_INPUT)
            .mapSlotToType(GrindstoneContainer.ADDITIONAL_SLOT, ContainerSlotType.GRINDSTONE_ADDITIONAL)
            .mapSlotToType(GrindstoneContainer.RESULT_SLOT, ContainerSlotType.GRINDSTONE_RESULT)
            .mapRangedNetworkSlotIndex(16, 18, 0)
            .build();

    public static final ContainerNetworkInfo CARTOGRAPHY_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CARTOGRAPHY)
            .type(ContainerTypes.CARTOGRAPHY_TABLE)
            .mapSlotToType(CartographyTableContainer.INPUT_SLOT, ContainerSlotType.CARTOGRAPHY_INPUT)
            .mapSlotToType(CartographyTableContainer.ADDITIONAL_SLOT, ContainerSlotType.CARTOGRAPHY_ADDITIONAL)
            .mapSlotToType(CartographyTableContainer.RESULT_SLOT, ContainerSlotType.CARTOGRAPHY_RESULT)
            .mapRangedNetworkSlotIndex(12, 14, 0)
            .build();

    public static final ContainerNetworkInfo LOOM = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.LOOM)
            .type(ContainerTypes.LOOM)
            .mapSlotToType(LoomContainer.INPUT_SLOT, ContainerSlotType.LOOM_INPUT)
            .mapSlotToType(LoomContainer.DYE_SLOT, ContainerSlotType.LOOM_DYE)
            .mapSlotToType(LoomContainer.MATERIAL_SLOT, ContainerSlotType.LOOM_MATERIAL)
            .mapSlotToType(LoomContainer.RESULT_SLOT, ContainerSlotType.LOOM_RESULT)
            .mapRangedNetworkSlotIndex(9, 12, 0)
            .build();

    public static final ContainerNetworkInfo SMITHING_TABLE = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.SMITHING_TABLE)
            .type(ContainerTypes.SMITHING_TABLE)
            .mapSlotToType(SmithingTableContainer.INPUT_SLOT, ContainerSlotType.SMITHING_TABLE_INPUT)
            .mapSlotToType(SmithingTableContainer.MATERIAL_SLOT, ContainerSlotType.SMITHING_TABLE_MATERIAL)
            .mapSlotToType(SmithingTableContainer.TEMPLATE_SLOT, ContainerSlotType.SMITHING_TABLE_TEMPLATE)
            .mapSlotToType(SmithingTableContainer.RESULT_SLOT, ContainerSlotType.SMITHING_TABLE_RESULT)
            .mapRangedNetworkSlotIndex(51, 54, 0)
            .build();

    public static final ContainerNetworkInfo DOUBLE_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.DOUBLE_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public static final ContainerNetworkInfo DISPENSER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.DISPENSER)
            .type(ContainerTypes.DISPENSER)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public static final ContainerNetworkInfo DROPPER = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.DROPPER)
            .type(ContainerTypes.DROPPER)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    // Fake Container Types

    public static final ContainerNetworkInfo FAKE_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.FAKE_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public static final ContainerNetworkInfo FAKE_DOUBLE_CHEST = builder()
            .networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.CONTAINER)
            .type(ContainerTypes.FAKE_DOUBLE_CHEST)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public ContainerNetworkInfo(int networkId, ContainerType<?> type, ContainerSlotType[] slotTypeTable, Set<ContainerSlotType> heldSlotTypes, BiMap<Integer, Integer> networkSlotIndexMapper) {
        this.networkId = networkId;
        this.type = type;
        this.slotTypeTable = slotTypeTable;
        // There shouldn't be null entry in slotTypeTable
        for (var slotType : slotTypeTable) {
            if (slotType == null) {
                throw new IllegalArgumentException("Slot type table shouldn't contain null entry!");
            }
        }
        this.heldSlotTypes = heldSlotTypes;
        this.networkSlotIndexMapper = networkSlotIndexMapper;
        TYPE_TO_INFO.put(type, this);
    }

    public static ContainerNetworkInfo getInfo(ContainerType<?> type) {
        return TYPE_TO_INFO.get(type);
    }

    public static Builder builder() {
        return new Builder();
    }

    public org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType toNetworkType() {
        return org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.from(networkId);
    }

    public ContainerSlotType getSlotType(int slot) {
        return slotTypeTable[slot];
    }

    public int size() {
        return slotTypeTable.length;
    }

    public static class Builder {
        private final Set<ContainerSlotType> heldSlotTypes = EnumSet.noneOf(ContainerSlotType.class);
        private final BiMap<Integer, Integer> networkSlotIndexMapper = HashBiMap.create();

        // Default id is the unknown network id
        private int networkId = Integer.MIN_VALUE;
        private ContainerType<?> type;
        private ContainerSlotType[] slotTypeTable;

        public Builder networkId(int id) {
            this.networkId = id;
            return this;
        }

        public Builder networkId(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType containerType) {
            this.networkId = containerType.getId();
            return this;
        }

        public Builder type(ContainerType<?> type) {
            this.type = type;
            this.slotTypeTable = new ContainerSlotType[type.getSize()];
            return this;
        }

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

        public Builder mapAllSlotToType(ContainerSlotType type) {
            if (slotTypeTable == null) {
                throw new IllegalStateException("The size must be set firstly!");
            }

            heldSlotTypes.add(type);
            Arrays.fill(slotTypeTable, type);
            return this;
        }

        public Builder mapSlotToType(int slot, ContainerSlotType type) {
            if (slotTypeTable == null) {
                throw new IllegalStateException("The size must be set firstly!");
            }

            heldSlotTypes.add(type);
            slotTypeTable[slot] = type;
            return this;
        }

        public Builder holdSlotType(ContainerSlotType type) {
            heldSlotTypes.add(type);
            return this;
        }

        public Builder mapNetworkSlotIndex(int networkSlotIndex, int slot) {
            networkSlotIndexMapper.put(networkSlotIndex, slot);
            return this;
        }

        public Builder mapRangedNetworkSlotIndex(int left, int right, int slot) {
            if (left > right) {
                throw new IllegalArgumentException("Left must smaller than right!");
            }

            for (int i = left, j = 0; i <= right; i++, j++) {
                networkSlotIndexMapper.put(i, slot + j);
            }
            return this;
        }

        public ContainerNetworkInfo build() {
            var allaySlotToNetworkSlot = networkSlotIndexMapper.inverse();
            // By default, the slot not specified to be mapped to the network slot is mapped to itself
            for (int i = 0; i < slotTypeTable.length; i++) {
                if (!allaySlotToNetworkSlot.containsKey(i)) {
                    networkSlotIndexMapper.put(i, i);
                }
            }
            return new ContainerNetworkInfo(networkId, type, slotTypeTable, heldSlotTypes, networkSlotIndexMapper);
        }
    }
}
