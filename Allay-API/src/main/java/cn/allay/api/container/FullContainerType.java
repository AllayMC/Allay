package cn.allay.api.container;

import cn.allay.api.container.impl.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

import java.util.*;

/**
 * Allay Project 2023/7/27
 *
 * @author daoge_cmd
 */
public record FullContainerType<T extends Container>(int id,
                                                     ContainerSlotType[] slotTypeTable,
                                                     Set<ContainerSlotType> heldSlotTypes) {

    public static final Map<ContainerSlotType, FullContainerType<? extends Container>> SLOT_TYPE_TO_TYPE_MAP = new EnumMap<>(ContainerSlotType.class);
    public static final int UNKNOWN_NETWORK_ID = Integer.MIN_VALUE;

    public static final FullContainerType<PlayerCursorContainer> CURSOR = builder()
            .id(124)
            .size(1)
            .mapAllSlotToType(ContainerSlotType.CURSOR)
            .build();

    public static final FullContainerType<PlayerArmorContainer> ARMOR = builder()
            .id(120)
            .size(4)
            .mapAllSlotToType(ContainerSlotType.ARMOR)
            .build();

    public static final FullContainerType<PlayerOffhandContainer> OFFHAND = builder()
            .id(119)
            .size(1)
            .mapAllSlotToType(ContainerSlotType.OFFHAND)
            .build();

    public static final FullContainerType<PlayerInventoryContainer> PLAYER_INVENTORY = builder()
            .id(ContainerType.INVENTORY)
            .size(36)
            .mapRangedSlotToType(0, 8, ContainerSlotType.HOTBAR)
            .mapRangedSlotToType(9, 35, ContainerSlotType.INVENTORY)
            .holdSlotType(ContainerSlotType.HOTBAR_AND_INVENTORY)
            .build();

    public static final FullContainerType<PlayerCreatedOutputContainer> CREATED_OUTPUT = builder()
            .id(UNKNOWN_NETWORK_ID)
            .size(1)
            .mapAllSlotToType(ContainerSlotType.CREATED_OUTPUT)
            .build();

    public static final FullContainerType<BarrelContainer> BARREL = builder()
            .id(ContainerType.CONTAINER)
            .size(27)
            .mapAllSlotToType(ContainerSlotType.BARREL)
            .build();

    public FullContainerType(int id, ContainerSlotType[] slotTypeTable, Set<ContainerSlotType> heldSlotTypes) {
        this.id = id;
        this.slotTypeTable = slotTypeTable;
        this.heldSlotTypes = heldSlotTypes;
        for (ContainerSlotType slotType : heldSlotTypes) {
            var mapped = SLOT_TYPE_TO_TYPE_MAP.get(slotType);
            if (mapped == null) {
                SLOT_TYPE_TO_TYPE_MAP.put(slotType, this);
                continue;
            }
            if (!mapped.equals(this))
                throw new IllegalArgumentException("Slot type " + slotType + " is already mapped to " + SLOT_TYPE_TO_TYPE_MAP.get(slotType));
        }
    }

    public static <T extends Container> FullContainerType<T> fromSlotType(ContainerSlotType type) {
        return (FullContainerType<T>) SLOT_TYPE_TO_TYPE_MAP.get(type);
    }

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
        private int id;
        private ContainerSlotType[] slotTypeTable;

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

        public <T extends Container> FullContainerType<T> build() {
            return new FullContainerType<>(id, slotTypeTable, heldSlotTypes);
        }
    }
}
