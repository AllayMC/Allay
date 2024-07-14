package org.allaymc.api.container;

import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3ic;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.allaymc.api.item.type.ItemTypes.AIR_TYPE;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface Container {

    // We can provide a null info for air type
    // And only for air type we can do that
    ItemStack EMPTY_SLOT_PLACE_HOLDER = AIR_TYPE.createItemStack(null);

    FullContainerType<?> getContainerType();

    void onOpen(ContainerViewer viewer);

    void onClose(ContainerViewer viewer);

    void addOnOpenListener(Consumer<ContainerViewer> listener);

    void removeOnOpenListener(Consumer<ContainerViewer> listener);

    void addOnCloseListener(Consumer<ContainerViewer> listener);

    void removeOnCloseListener(Consumer<ContainerViewer> listener);

    default boolean hasBlockPos() {
        return false;
    }

    default Vector3ic getBlockPos() {
        throw new UnsupportedOperationException();
    }

    default void setBlockPos(Vector3ic blockPos) {
        // Do nothing in default
    }

    default ContainerSlotType getSlotType(int slot) {
        return getContainerType().getSlotType(slot);
    }

    Map<Byte, ContainerViewer> getViewers();

    ItemStack getItemStack(int slot);

    default boolean isEmpty(int slot) {
        return getItemStack(slot) == EMPTY_SLOT_PLACE_HOLDER;
    }

    default boolean isEmpty() {
        for (ItemStack itemStack : getItemStackArray()) {
            if (itemStack != EMPTY_SLOT_PLACE_HOLDER) {
                return false;
            }
        }
        return true;
    }

    @UnmodifiableView
    List<ItemStack> getItemStacks();

    @UnmodifiableView
    ItemStack[] getItemStackArray();

    List<ItemData> toNetworkItemData();

    void setItemStack(int slot, ItemStack itemStack);

    default void clearSlot(int slot) {
        setItemStack(slot, EMPTY_SLOT_PLACE_HOLDER);
    }

    default void clearAllSlots() {
        for (int slot = 0; slot < getItemStackArray().length; slot++) {
            clearSlot(slot);
        }
    }

    void addViewer(ContainerViewer viewer);

    void removeViewer(ContainerViewer viewer);

    default void removeAllViewers() {
        getViewers().values().forEach(this::removeViewer);
    }

    ContainerViewer removeViewer(byte viewerId);

    void onSlotChange(int slot);

    List<NbtMap> saveNBT();

    void loadNBT(List<NbtMap> nbtList);

    default int toNetworkSlotIndex(int index) {
        return getContainerType().networkSlotIndexMapper().inverse().get(index);
    }

    default int fromNetworkSlotIndex(int index) {
        return getContainerType().networkSlotIndexMapper().get(index);
    }

    default void sendContents(ContainerViewer viewer) {
        viewer.sendContents(this);
    }

    default void sendContent(ContainerViewer viewer, int slot) {
        viewer.sendContent(this, slot);
    }

    default int tryAddItem(ItemStack itemStack, int minSlotIndex, int maxSlotIndex) {
        if (minSlotIndex > maxSlotIndex) {
            throw new IllegalArgumentException("minSlotIndex > maxSlotIndex");
        }
        if (minSlotIndex < 0) {
            throw new IllegalArgumentException("minSlotIndex is less than 0");
        }
        ItemStack[] itemStacks = getItemStackArray();
        if (minSlotIndex > itemStacks.length - 1 || maxSlotIndex > itemStacks.length - 1) {
            throw new IllegalArgumentException("minSlotIndex or maxSlotIndex is out of range");
        }
        var minEmptySlot = -1;
        // Find out the min empty slot
        for (int index = minSlotIndex; index <= maxSlotIndex; index++) {
            if (itemStacks[index] == Container.EMPTY_SLOT_PLACE_HOLDER) {
                minEmptySlot = index;
                break;
            }
        }
        // First, try to merge with other item stack
        for (int index = minSlotIndex; index <= maxSlotIndex; index++) {
            var content = itemStacks[index];
//            if (content == Container.EMPTY_SLOT_PLACE_HOLDER) {
//                if (minEmptySlot == -1) {
//                    minEmptySlot = index;
//                }
//                continue;
//            }
            if (content.getCount() != content.getItemData().maxStackSize() && content.canMerge(itemStack, true)) {
                if (content.getCount() + itemStack.getCount() <= content.getItemData().maxStackSize()) {
                    content.setCount(content.getCount() + itemStack.getCount());
                    itemStack.setCount(0);
                } else {
                    int count = itemStack.getCount();
                    int completion = content.getItemData().maxStackSize() - content.getCount();
                    itemStack.setCount(count - completion);
                    content.setCount(content.getItemData().maxStackSize());
                }
                onSlotChange(index);
                if (itemStack.getCount() == 0) {
                    return index;
                }
            }
        }
        // Second, put the item on an empty slot (if exists)
        if (minEmptySlot != -1) {
            setItemStack(minEmptySlot, itemStack.copy());
            itemStack.setCount(0);
            return minEmptySlot;
        }
        return -1;
    }

    default int tryAddItem(ItemStack itemStack) {
        var array = getItemStackArray();
        return tryAddItem(itemStack, 0, array.length - 1);
    }
}
