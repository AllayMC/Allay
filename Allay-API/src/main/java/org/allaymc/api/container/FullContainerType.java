package org.allaymc.api.container;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.container.impl.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

import java.util.*;

/**
 * Allay Project 2023/7/27
 *
 * @author daoge_cmd
 */
public record FullContainerType<T extends Container>(
        int id,
        ContainerSlotType[] slotTypeTable,
        Set<ContainerSlotType> heldSlotTypes,
        // 原版对于某些容器提供的slot范围与allay的设计不符，例如工作台：原版为32-39，而allay为0-8
        // 此双向映射表用于解决上诉问题
        // The slot range provided by vanilla for some containers does not match the design of the allay. For example, crafting table: 32-39 in vanilla but 0-8 in allay
        // This bidirectional mapping table is used to resolve appeals
        // network slot <-> slot that allay used
        BiMap<Integer, Integer> networkSlotIndexMapper
) {

//    public static final Map<ContainerSlotType, FullContainerType<? extends Container>> SLOT_TYPE_TO_TYPE_MAP = new EnumMap<>(ContainerSlotType.class);
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

    public static final FullContainerType<CraftingGridContainer> CRAFTING_GRID = builder()
            .id(ContainerType.WORKBENCH)
            //Small Grid(2x2) + Crafting Table Grid(3x3)
            .size(4 + 9)
            .mapAllSlotToType(ContainerSlotType.CRAFTING_INPUT)
            .mapRangedNetworkSlotIndex(28, 40, 0)
            .build();

    public FullContainerType(int id, ContainerSlotType[] slotTypeTable, Set<ContainerSlotType> heldSlotTypes, BiMap<Integer, Integer> networkSlotIndexMapper) {
        this.id = id;
        this.slotTypeTable = slotTypeTable;
        //There shouldn't be null entry in slotTypeTable
        for (var slotType : slotTypeTable) {
            if (slotType == null) throw new IllegalArgumentException("Slot type table shouldn't contain null entry!");
        }
        this.heldSlotTypes = heldSlotTypes;
        this.networkSlotIndexMapper = networkSlotIndexMapper;
    }

//    public static <T extends Container> FullContainerType<T> fromSlotType(ContainerSlotType type) {
//        return (FullContainerType<T>) SLOT_TYPE_TO_TYPE_MAP.get(type);
//    }

    public static FullContainerTypeBuilder builder() {
        return new FullContainerTypeBuilder();
    }

    public ContainerType toNetworkType() {
        return ContainerType.from(id);
    }

    public ContainerSlotType getSlotType(int slot) {
        return slotTypeTable[slot];
    }

    public int size() {
        return slotTypeTable().length;
    }

    public static class FullContainerTypeBuilder {
        private final Set<ContainerSlotType> heldSlotTypes = EnumSet.noneOf(ContainerSlotType.class);
        private int id = UNKNOWN_NETWORK_ID;
        private ContainerSlotType[] slotTypeTable;
        BiMap<Integer, Integer> networkSlotIndexMapper = HashBiMap.create();

        public FullContainerTypeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public FullContainerTypeBuilder id(ContainerType containerType) {
            this.id = containerType.getId();
            return this;
        }

        public FullContainerTypeBuilder size(int size) {
            this.slotTypeTable = new ContainerSlotType[size];
            return this;
        }

        public FullContainerTypeBuilder mapRangedSlotToType(int l, int r, ContainerSlotType type) {
            if (slotTypeTable == null) throw new IllegalStateException("The size must be set firstly!");
            if (l > r) throw new IllegalArgumentException("Left must smaller than right!");
            if (l > slotTypeTable.length || r > slotTypeTable.length)
                throw new IllegalArgumentException("Left or right bigger than size!");
            heldSlotTypes.add(type);
            for (int i = l; i <= r; i++) {
                slotTypeTable[i] = type;
            }
            return this;
        }

        public FullContainerTypeBuilder mapAllSlotToType(ContainerSlotType type) {
            if (slotTypeTable == null) throw new IllegalStateException("The size must be set firstly!");
            heldSlotTypes.add(type);
            Arrays.fill(slotTypeTable, type);
            return this;
        }

        public FullContainerTypeBuilder holdSlotType(ContainerSlotType type) {
            heldSlotTypes.add(type);
            return this;
        }

        public FullContainerTypeBuilder mapNetworkSlotIndex(int networkSlotIndex, int slot) {
            networkSlotIndexMapper.put(networkSlotIndex, slot);
            return this;
        }

        public FullContainerTypeBuilder mapRangedNetworkSlotIndex(int l, int r, int slot) {
            if (l > r) throw new IllegalArgumentException("Left must smaller than right!");
            for (int i = l, j = 0; i <= r; i++, j++) {
                networkSlotIndexMapper.put(i, slot + j);
            }
            return this;
        }

        public <T extends Container> FullContainerType<T> build() {
            var allaySlotToNetworkSlot = networkSlotIndexMapper.inverse();
            // 默认将未指定network slot映射的slot映射到自身
            // By default, the slot that is not specified to be mapped to the network slot is mapped to itself
            for (int i = 0; i < slotTypeTable.length; i++) {
                if (!allaySlotToNetworkSlot.containsKey(i)) {
                    networkSlotIndexMapper.put(i, i);
                }
            }
            return new FullContainerType<>(id, slotTypeTable, heldSlotTypes, networkSlotIndexMapper);
        }
    }
}
