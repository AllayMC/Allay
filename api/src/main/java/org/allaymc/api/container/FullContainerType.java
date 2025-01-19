package org.allaymc.api.container;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.container.impl.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * FullContainerType is a complete description of a container type.
 *
 * @author daoge_cmd
 */
public record FullContainerType<T extends Container>(
        int id,
        ContainerSlotType[] slotTypeTable,
        Set<ContainerSlotType> heldSlotTypes,
        // The slot range provided by vanilla for some containers does not match the design of Allay.
        // For example, crafting table: 32-39 in vanilla but 0-8 in Allay
        // This bidirectional mapping table is used to resolve these issues
        // network slot <-> slot that Allay used
        BiMap<Integer, Integer> networkSlotIndexMapper
) {

    public static final int UNKNOWN_NETWORK_ID = Integer.MIN_VALUE;

    public static final FullContainerType<PlayerCursorContainer> CURSOR = builder()
            .size(1)
            .mapAllSlotToType(ContainerSlotType.CURSOR)
            .build();

    public static final FullContainerType<PlayerArmorContainer> ARMOR = builder()
            .size(4)
            .mapAllSlotToType(ContainerSlotType.ARMOR)
            .build();

    public static final FullContainerType<PlayerOffhandContainer> OFFHAND = builder()
            .size(1)
            .mapAllSlotToType(ContainerSlotType.OFFHAND)
            .mapNetworkSlotIndex(1, 0)
            .build();

    public static final FullContainerType<PlayerInventoryContainer> PLAYER_INVENTORY = builder()
            .id(ContainerType.INVENTORY)
            .size(36)
            .mapRangedSlotToType(0, 8, ContainerSlotType.HOTBAR)
            .mapRangedSlotToType(9, 35, ContainerSlotType.INVENTORY)
            .holdSlotType(ContainerSlotType.HOTBAR_AND_INVENTORY)
            .build();

    public static final FullContainerType<PlayerCreatedOutputContainer> CREATED_OUTPUT = builder()
            .size(1)
            .mapAllSlotToType(ContainerSlotType.CREATED_OUTPUT)
            .mapNetworkSlotIndex(50, 0)
            .build();

    public static final FullContainerType<BarrelContainer> BARREL = builder()
            .id(ContainerType.CONTAINER)
            .size(27)
            .mapAllSlotToType(ContainerSlotType.BARREL)
            .build();

    public static final FullContainerType<ChestContainer> CHEST = builder()
            .id(ContainerType.CONTAINER)
            .size(27)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public static final FullContainerType<CraftingGridContainer> CRAFTING_GRID = builder()
            .id(ContainerType.INVENTORY)
            .size(4)
            .mapAllSlotToType(ContainerSlotType.CRAFTING_INPUT)
            .mapRangedNetworkSlotIndex(28, 31, 0)
            .build();

    public static final FullContainerType<CraftingTableContainer> CRAFTING_TABLE = builder()
            .id(ContainerType.WORKBENCH)
            .size(9)
            .mapAllSlotToType(ContainerSlotType.CRAFTING_INPUT)
            .mapRangedNetworkSlotIndex(32, 40, 0)
            .build();

    public static final FullContainerType<ShulkerBoxContainer> SHULKER_BOX = builder()
            .id(ContainerType.CONTAINER)
            .size(27)
            .mapAllSlotToType(ContainerSlotType.SHULKER_BOX)
            .build();

    public static final FullContainerType<FurnaceContainer> FURNACE = builder()
            .id(ContainerType.FURNACE)
            .size(3)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.FURNACE_INGREDIENT)
            .build();

    public static final FullContainerType<FurnaceContainer> BLAST_FURNACE = builder()
            .id(ContainerType.BLAST_FURNACE)
            .size(3)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.BLAST_FURNACE_INGREDIENT)
            .build();

    public static final FullContainerType<FurnaceContainer> SMOKER = builder()
            .id(ContainerType.SMOKER)
            .size(3)
            .mapSlotToType(FurnaceContainer.RESULT_SLOT, ContainerSlotType.FURNACE_RESULT)
            .mapSlotToType(FurnaceContainer.FUEL_SLOT, ContainerSlotType.FURNACE_FUEL)
            .mapSlotToType(FurnaceContainer.INGREDIENT_SLOT, ContainerSlotType.SMOKER_INGREDIENT)
            .build();

    public static final FullContainerType<EnchantTableContainer> ENCHANT_TABLE = builder()
            .id(ContainerType.ENCHANTMENT)
            .size(2)
            .mapSlotToType(EnchantTableContainer.INPUT_SLOT, ContainerSlotType.ENCHANTING_INPUT)
            .mapSlotToType(EnchantTableContainer.MATERIAL_SLOT, ContainerSlotType.ENCHANTING_MATERIAL)
            .mapRangedNetworkSlotIndex(14, 15, 0)
            .build();

    public static final FullContainerType<BeaconContainer> BEACON = builder()
            .id(ContainerType.BEACON)
            .size(1)
            .mapAllSlotToType(ContainerSlotType.BEACON_PAYMENT)
            .mapNetworkSlotIndex(27, BeaconContainer.BEACON_PAYMENT_SLOT)
            .build();

    public static final FullContainerType<BrewingStandContainer> BREWING_STAND = builder()
            .id(ContainerType.BREWING_STAND)
            .size(5)
            .mapSlotToType(BrewingStandContainer.REAGENT_SLOT, ContainerSlotType.BREWING_INPUT)
            .mapRangedSlotToType(1, 3, ContainerSlotType.BREWING_RESULT)
            .mapSlotToType(BrewingStandContainer.FUEL_SLOT, ContainerSlotType.BREWING_FUEL)
            .build();

    public static final FullContainerType<EnderChestContainer> ENDER_CHEST = builder()
            .id(ContainerType.CONTAINER)
            .size(27)
            .mapAllSlotToType(ContainerSlotType.LEVEL_ENTITY)
            .build();

    public FullContainerType(int id, ContainerSlotType[] slotTypeTable, Set<ContainerSlotType> heldSlotTypes, BiMap<Integer, Integer> networkSlotIndexMapper) {
        this.id = id;
        this.slotTypeTable = slotTypeTable;
        // There shouldn't be null entry in slotTypeTable
        for (var slotType : slotTypeTable) {
            if (slotType == null) {
                throw new IllegalArgumentException("Slot type table shouldn't contain null entry!");
            }
        }
        this.heldSlotTypes = heldSlotTypes;
        this.networkSlotIndexMapper = networkSlotIndexMapper;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ContainerType toNetworkType() {
        return ContainerType.from(id);
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

        private int id = UNKNOWN_NETWORK_ID;
        private ContainerSlotType[] slotTypeTable;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder id(ContainerType containerType) {
            this.id = containerType.getId();
            return this;
        }

        public Builder size(int size) {
            this.slotTypeTable = new ContainerSlotType[size];
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

        public <T extends Container> FullContainerType<T> build() {
            var allaySlotToNetworkSlot = networkSlotIndexMapper.inverse();
            // By default, the slot not specified to be mapped to the network slot is mapped to itself
            for (int i = 0; i < slotTypeTable.length; i++) {
                if (!allaySlotToNetworkSlot.containsKey(i)) {
                    networkSlotIndexMapper.put(i, i);
                }
            }
            return new FullContainerType<>(id, slotTypeTable, heldSlotTypes, networkSlotIndexMapper);
        }
    }
}
