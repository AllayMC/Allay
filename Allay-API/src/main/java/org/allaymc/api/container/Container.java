package org.allaymc.api.container;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
public interface Container {

    FullContainerType<?> getContainerType();

    void onOpen(ContainerViewer viewer);

    void onClose(ContainerViewer viewer);

    void addOnOpenListener(Consumer<ContainerViewer> listener);

    void removeOnOpenListener(Consumer<ContainerViewer> listener);

    void addOnCloseListener(Consumer<ContainerViewer> listener);

    void removeOnCloseListener(Consumer<ContainerViewer> listener);

    void addOnSlotChangeListener(int slot, Consumer<ItemStack> listener);

    void removeOnSlotChangeListener(int slot, Consumer<ItemStack> listener);

    default ContainerSlotType getSlotType(int slot) {
        return getContainerType().getSlotType(slot);
    }

    Map<Byte, ContainerViewer> getViewers();

    ItemStack getItemStack(int slot);

    default boolean isEmpty(int slot) {
        return getItemStack(slot) == ItemAirStack.AIR_STACK;
    }

    default boolean isEmpty() {
        for (ItemStack itemStack : getItemStackArray()) {
            if (itemStack != ItemAirStack.AIR_STACK) {
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
        setItemStack(slot, ItemAirStack.AIR_STACK);
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

    default void onAllSlotsChange() {
        for (int slot = 0; slot < getItemStackArray().length; slot++) {
            onSlotChange(slot);
        }
    }

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
            if (itemStacks[index] == ItemAirStack.AIR_STACK) {
                minEmptySlot = index;
                break;
            }
        }
        // Firstly, try to merge with other item stack
        for (int index = minSlotIndex; index <= maxSlotIndex; index++) {
            var content = itemStacks[index];
            if (!content.isFull() && content.canMerge(itemStack, true)) {
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
        // Secondly, put the item on an empty slot (if exists)
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

    default void sendContainerData(int property, int value) {
        getViewers().forEach((id, viewer) -> viewer.sendContainerData(id, property, value));
    }
}
