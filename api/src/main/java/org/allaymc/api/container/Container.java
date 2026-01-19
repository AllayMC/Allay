package org.allaymc.api.container;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Container represents an object that can hold items, and it can be viewed by {@link ContainerViewer}.
 *
 * @author daoge_cmd
 */
public interface Container {

    /**
     * Get the container type.
     *
     * @return the container type
     */
    ContainerType<?> getContainerType();

    /**
     * Add a listener to be called when the container is opened.
     *
     * @param listener the listener
     */
    void addOpenListener(Consumer<ContainerViewer> listener);

    /**
     * Remove a listener to be called when the container is opened.
     *
     * @param listener the listener
     */
    void removeOpenListener(Consumer<ContainerViewer> listener);

    /**
     * Add a listener to be called when the container is closed.
     *
     * @param listener the listener
     */
    void addCloseListener(Consumer<ContainerViewer> listener);

    /**
     * Remove a listener to be called when the container is closed.
     *
     * @param listener the listener
     */
    void removeCloseListener(Consumer<ContainerViewer> listener);

    /**
     * Add a listener to be called when the slot is changed.
     *
     * @param slot     the slot
     * @param listener the listener
     */
    void addSlotChangeListener(int slot, Consumer<ItemStack> listener);

    /**
     * Remove a listener to be called when the slot is changed.
     *
     * @param slot     the slot
     * @param listener the listener
     */
    void removeSlotChangeListener(int slot, Consumer<ItemStack> listener);

    /**
     * Get the viewers of the container.
     *
     * @return the viewers
     */
    @UnmodifiableView
    Map<Byte, ContainerViewer> getViewers();

    /**
     * Get the item stack of the slot.
     *
     * @param slot the slot
     * @return the item stack
     */
    ItemStack getItemStack(int slot);

    /**
     * Check if the slot is empty.
     *
     * @param slot the slot
     * @return {@code true} if the slot is empty, otherwise {@code false}.
     */
    default boolean isEmpty(int slot) {
        return getItemStack(slot) == ItemAirStack.AIR_STACK;
    }

    /**
     * Check if the container is empty.
     *
     * @return {@code true} if the container is empty, otherwise {@code false}.
     */
    default boolean isEmpty() {
        for (ItemStack itemStack : getItemStackArray()) {
            if (itemStack != ItemAirStack.AIR_STACK) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the container is full.
     *
     * @return {@code true} if the container is full, otherwise {@code false}.
     */
    default boolean isFull() {
        for (var stack : getItemStackArray()) {
            if (stack == ItemAirStack.AIR_STACK || !stack.isFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the item stacks of the container.
     *
     * @return the item stacks
     */
    @UnmodifiableView
    List<ItemStack> getItemStacks();

    /**
     * Get the item stack array of the container.
     *
     * @return the item stack array
     */
    @UnmodifiableView
    ItemStack[] getItemStackArray();

    /**
     * @see #setItemStack(int, ItemStack, boolean)
     */
    default void setItemStack(int slot, ItemStack itemStack) {
        setItemStack(slot, itemStack, true);
    }

    /**
     * Sets the item stack at the specified slot.
     *
     * @param slot      the slot index
     * @param itemStack the {@link ItemStack} to set
     *                  If {@link ItemAirStack#AIR_STACK}, clears the slot.
     * @param send      whether to send an update packet to viewers
     * @throws NullPointerException if {@code itemStack} is {@code null}
     */
    void setItemStack(int slot, ItemStack itemStack, boolean send);

    /**
     * @see #clearSlot(int, boolean)
     */
    default void clearSlot(int slot) {
        clearSlot(slot, true);
    }

    /**
     * Clears the specified slot by setting it to {@link ItemAirStack#AIR_STACK}.
     *
     * @param slot the slot index
     * @param send whether to send an update packet to viewers
     */
    default void clearSlot(int slot, boolean send) {
        setItemStack(slot, ItemAirStack.AIR_STACK, send);
    }

    /**
     * Clears all slots in the container.
     */
    default void clearAllSlots() {
        for (int slot = 0; slot < getItemStackArray().length; slot++) {
            clearSlot(slot);
        }
    }

    /**
     * Adds a viewer to this container.
     *
     * @param viewer the {@link ContainerViewer} to add
     * @return {@code true} if the viewer was added successfully, {@code false} otherwise
     */
    boolean addViewer(ContainerViewer viewer);

    /**
     * Removes a viewer from this container.
     *
     * @param viewer the {@link ContainerViewer} to remove
     * @return {@code true} if the viewer was removed successfully, {@code false} otherwise
     */
    boolean removeViewer(ContainerViewer viewer);

    /**
     * Removes all viewers from this container.
     */
    default void removeAllViewers() {
        new ArrayList<>(getViewers().values()).forEach(this::removeViewer);
    }

    /**
     * @see #notifySlotChange(int, boolean)
     */
    default void notifySlotChange(int slot) {
        notifySlotChange(slot, true);
    }

    /**
     * Notifies viewers that the item in the specified slot has changed.
     * <p>
     * This method should be called after modifying a slot's item to ensure viewers are updated.
     *
     * @param slot the slot index
     * @param send whether to send an update packet to viewers
     */
    void notifySlotChange(int slot, boolean send);

    /**
     * Notifies viewers that all slots have changed.
     * <p>
     * This method should be called after modifying all slots to ensure viewers are updated.
     */
    default void notifyAllSlotsChange() {
        for (int slot = 0; slot < getItemStackArray().length; slot++) {
            notifySlotChange(slot);
        }
    }

    /**
     * Save the container to NBT.
     *
     * @return the NBT list
     */
    List<NbtMap> saveNBT();

    /**
     * Load the container from NBT.
     *
     * @param nbtList the NBT list
     */
    void loadNBT(List<NbtMap> nbtList);

    /**
     * Send the contents of the container to the viewer.
     *
     * @param viewer the viewer
     */
    default void sendContents(ContainerViewer viewer) {
        viewer.viewContents(this);
    }

    /**
     * Send content of a specific slot to the viewer.
     *
     * @param viewer the viewer
     * @param slot   the slot
     */
    default void sendContent(ContainerViewer viewer, int slot) {
        viewer.viewSlot(this, slot);
    }

    /**
     * Try to add an item to the specified slot range of this container.
     *
     * @param itemStack    the item stack
     * @param minSlotIndex the min slot index
     * @param maxSlotIndex the max slot index
     * @return the slot index where the item is added, or {@code -1} if the item is failed to be added
     */
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
                if (content.getCount() + itemStack.getCount() <= content.getItemType().getItemData().maxStackSize()) {
                    content.setCount(content.getCount() + itemStack.getCount());
                    itemStack.setCount(0);
                } else {
                    int count = itemStack.getCount();
                    int completion = content.getItemType().getItemData().maxStackSize() - content.getCount();
                    itemStack.setCount(count - completion);
                    content.setCount(content.getItemType().getItemData().maxStackSize());
                }
                notifySlotChange(index);
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

    /**
     * Try to add an item to the container.
     *
     * @param itemStack the item stack
     * @return the slot index where the item is added, or {@code -1} if the item is failed to be added
     */
    default int tryAddItem(ItemStack itemStack) {
        var array = getItemStackArray();
        return tryAddItem(itemStack, 0, array.length - 1);
    }

    /**
     * Send container data to the viewers.
     *
     * @param property the property
     * @param value    the value
     */
    default void sendContainerData(int property, int value) {
        getViewers().forEach(($, viewer) -> viewer.viewContainerData(this, property, value));
    }

    /**
     * Calculates the redstone signal strength for this container based on its contents.
     * <p>
     * The formula is: floor(averageFullness * 14) + (hasItems ? 1 : 0)
     * where averageFullness = sum of (itemCount / maxStackSize) / totalSlots
     * <p>
     * This matches vanilla Minecraft behavior for comparator signals.
     *
     * @return the redstone signal strength (0-15)
     */
    default int calculateComparatorSignal() {
        ItemStack[] items = getItemStackArray();
        if (items.length == 0) {
            return 0;
        }

        int itemCount = 0;
        float totalFullness = 0.0f;

        for (ItemStack item : items) {
            if (item != ItemAirStack.AIR_STACK) {
                int maxStackSize = item.getItemType().getItemData().maxStackSize();
                totalFullness += (float) item.getCount() / (float) maxStackSize;
                itemCount++;
            }
        }

        if (itemCount == 0) {
            return 0;
        }

        float averageFullness = totalFullness / (float) items.length;
        return (int) Math.floor(averageFullness * 14.0f) + 1;
    }
}
