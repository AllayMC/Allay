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
 * {@code Container} exposes item slots, viewer membership, change listeners, and item
 * persistence for inventories and workstation interfaces. Stored item stacks are mutable
 * values, and ordinary server implementations expose those same values through their
 * accessors. Callers that edit a stored stack directly must use the slot notification
 * operations so observers can see the change.
 *
 * <p>
 * The default insertion helper merges into existing stacks before using an empty slot
 * and reduces the supplied stack to represent the remainder. A failure result can
 * therefore follow partial insertion. Comparator calculation combines each stack's
 * fullness with the total slot count, including empty slots, to compute the container's
 * redstone output.
 *
 * @author daoge_cmd
 */
public interface Container {

    /**
     * Returns the identity token describing this container's slot layout.
     */
    ContainerType<?> getContainerType();

    /**
     * Registers a listener for a viewer successfully opening this container. The server
     * invokes listeners after recording viewer membership, so callbacks can inspect the
     * newly opened container's viewer map.
     */
    void addOpenListener(Consumer<ContainerViewer> listener);

    /**
     * Removes the supplied listener from future successful-open notifications. The
     * server compares listener registrations by set membership; callers should supply
     * the listener that they previously registered with this container.
     */
    void removeOpenListener(Consumer<ContainerViewer> listener);

    /**
     * Registers a listener for a registered viewer leaving this container. The server
     * invokes listeners after removing membership and notifying the viewer that its
     * container window is closed.
     */
    void addCloseListener(Consumer<ContainerViewer> listener);

    /**
     * Removes the supplied listener from future successful-close notifications. An
     * absent registration has no effect; callers should retain the listener instance
     * used when registering for later removal.
     */
    void removeCloseListener(Consumer<ContainerViewer> listener);

    /**
     * Registers a listener for notifications on the specified container slot. Listeners
     * receive the stored stack reference even when a notification suppresses the
     * ordinary packet update to container viewers.
     */
    void addSlotChangeListener(int slot, Consumer<ItemStack> listener);

    /**
     * Removes the supplied listener from notifications for one container slot. Other
     * slots and other registrations remain unaffected; the {@code listener} argument identifies
     * the registration to remove from that slot.
     */
    void removeSlotChangeListener(int slot, Consumer<ItemStack> listener);

    /**
     * Returns an unmodifiable view of viewers indexed by their assigned window IDs.
     * Membership changes remain visible through this view; the entries refer to the
     * registered viewer objects rather than copies.
     */
    @UnmodifiableView
    Map<Byte, ContainerViewer> getViewers();

    /**
     * Returns the stack stored at the zero-based container slot. The stack remains
     * shared with the container, so callers must notify the slot after directly changing
     * the returned value.
     */
    ItemStack getItemStack(int slot);

    /**
     * Returns whether the slot contains the shared air stack sentinel.
     */
    default boolean isEmpty(int slot) {
        return getItemStack(slot) == ItemAirStack.AIR_STACK;
    }

    /**
     * Returns whether every slot contains the shared air stack sentinel.
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
     * Returns whether every slot contains a non-air, full item stack.
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
     * Returns the container's item stacks in slot order. {@code BaseContainer} returns
     * an unmodifiable live view, but {@code DoubleChestContainerImpl} currently returns
     * a new mutable list; both expose shared mutable stack references requiring change
     * notifications.
     */
    @UnmodifiableView
    List<ItemStack> getItemStacks();

    /**
     * Returns the array representation of this container's stored stacks. {@code
     * BaseContainer} returns its backing array despite the view annotation, while {@code
     * DoubleChestContainerImpl} copies the array structure; callers should use setters
     * and notifications for changes.
     */
    @UnmodifiableView
    ItemStack[] getItemStackArray();

    /**
     * Replaces one slot and requests the ordinary viewer update. The supplied stack must
     * be non-{@code null}; this overload delegates to the assignment operation with
     * {@code send} set to {@code true}.
     */
    default void setItemStack(int slot, ItemStack itemStack) {
        setItemStack(slot, itemStack, true);
    }

    /**
     * Replaces one slot with the supplied non-{@code null} stack and notifies its
     * listeners. {@code send} controls ordinary container viewer updates; the stored
     * stack is retained rather than copied.
     */
    void setItemStack(int slot, ItemStack itemStack, boolean send);

    /**
     * Clears one slot and requests ordinary viewer synchronization. The shared air stack
     * becomes its stored value, and the assignment path still notifies registered
     * slot-change listeners for that slot.
     */
    default void clearSlot(int slot) {
        clearSlot(slot, true);
    }

    /**
     * Replaces one slot with the shared air stack and notifies its listeners. {@code
     * send} selects whether the ordinary container viewer update is requested alongside
     * that slot notification.
     */
    default void clearSlot(int slot, boolean send) {
        setItemStack(slot, ItemAirStack.AIR_STACK, send);
    }

    /**
     * Clears every slot in ascending index order using ordinary slot assignments. Each
     * assignment can notify viewers and listeners immediately, so observers may see the
     * container while later slots remain uncleared.
     */
    default void clearAllSlots() {
        for (int slot = 0; slot < getItemStackArray().length; slot++) {
            clearSlot(slot);
        }
    }

    /**
     * Attempts to open this container for the viewer and establish membership. {@code
     * false} indicates rejection, including a cancelled opening event; an already
     * registered viewer can return {@code true} without another opening.
     */
    boolean addViewer(ContainerViewer viewer);

    /**
     * Attempts to remove the viewer and close its container interface. The return value
     * reports whether membership was removed; the close event can still be raised when
     * no membership exists.
     */
    boolean removeViewer(ContainerViewer viewer);

    /**
     * Attempts to remove each viewer present when this operation starts. The copied
     * membership list permits ordinary removals to change the live viewer map while
     * subsequent close callbacks are processed.
     */
    default void removeAllViewers() {
        new ArrayList<>(getViewers().values()).forEach(this::removeViewer);
    }

    /**
     * Notifies listeners and requests ordinary viewer synchronization for the current
     * stack in one slot. Callers use this after changing a shared stored stack without
     * assigning a replacement through the setter.
     */
    default void notifySlotChange(int slot) {
        notifySlotChange(slot, true);
    }

    /**
     * Publishes a slot's current stack to its listeners and, when requested, container
     * viewers. {@code send} suppresses only the ordinary viewer update; listeners still
     * run and may perform additional effects.
     */
    void notifySlotChange(int slot, boolean send);

    /**
     * Publishes ordinary notifications for every container slot in ascending index
     * order. This operation does not replace stacks; callers can use it after directly
     * updating multiple stored stack values.
     */
    default void notifyAllSlotsChange() {
        for (int slot = 0; slot < getItemStackArray().length; slot++) {
            notifySlotChange(slot);
        }
    }

    /**
     * Serializes the nonempty container slots into item NBT entries. Empty slots are
     * omitted by this overload, while each saved entry retains its slot index for a
     * later container load.
     */
    default List<NbtMap> saveNBT() {
        return saveNBT(false);
    }

    /**
     * Serializes item stacks with their container slot indices. {@code saveEmptySlots}
     * includes the air slots when {@code true}; otherwise only occupied slots contribute
     * entries to the returned list.
     */
    List<NbtMap> saveNBT(boolean saveEmptySlots);

    /**
     * Loads item entries into the slots named by their NBT slot fields. Server loading
     * leaves unspecified slots unchanged and does not send viewer updates, so callers
     * arrange synchronization after loading.
     */
    void loadNBT(List<NbtMap> nbtList);

    /**
     * Sends this container's current contents through the viewer's content callback. The
     * viewer must recognize the container as open or otherwise support its built-in
     * container identity before accepting the update.
     */
    default void sendContents(ContainerViewer viewer) {
        viewer.viewContainerContents(this);
    }

    /**
     * Sends one container slot through the viewer's slot callback. The viewer must
     * recognize the container as open or otherwise support its built-in identity before
     * accepting this targeted update.
     */
    default void sendContent(ContainerViewer viewer, int slot) {
        viewer.viewContainerSlot(this, slot);
    }

    /**
     * Adds as much of {@code itemStack} as possible within an inclusive slot range. The
     * range must be ordered, nonnegative, and contained in the current slot array;
     * invalid ranges throw {@code IllegalArgumentException} before insertion starts.
     *
     * <p>
     * Existing compatible stacks receive items before the first empty slot is used. Each
     * merge reduces the supplied stack and immediately notifies the changed slot. An
     * empty slot receives a copy of the remaining stack.
     *
     * <p>
     * The result is the final receiving slot when all items are inserted, or {@code -1}
     * when no empty slot remains after merging. That failure can leave earlier merges
     * applied, with the uninserted count remaining in {@code itemStack}.
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
        // Remember the first empty slot before merging, so any remaining items use
        // the lowest available index in this range.
        for (int index = minSlotIndex; index <= maxSlotIndex; index++) {
            if (itemStacks[index] == ItemAirStack.AIR_STACK) {
                minEmptySlot = index;
                break;
            }
        }
        // Merge into compatible occupied stacks before consuming empty capacity;
        // earlier merges remain applied if no empty slot can receive the remainder.
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
        // Copy the remainder into the first empty slot before zeroing the caller's
        // stack, so the inserted count remains available in storage.
        if (minEmptySlot != -1) {
            setItemStack(minEmptySlot, itemStack.copy());
            itemStack.setCount(0);
            return minEmptySlot;
        }
        return -1;
    }

    /**
     * Attempts insertion across every slot and reduces {@code itemStack} to its
     * uninserted remainder. A result of {@code -1} can follow partial merging; an empty
     * container layout produces an invalid range exception.
     */
    default int tryAddItem(ItemStack itemStack) {
        var array = getItemStackArray();
        return tryAddItem(itemStack, 0, array.length - 1);
    }

    /**
     * Sends a numeric container property and value to every registered viewer. The
     * property's meaning and units depend on the window type, and this operation changes
     * no item stacks.
     */
    default void sendContainerData(int property, int value) {
        getViewers().forEach(($, viewer) -> viewer.viewContainerData(this, property, value));
    }

    /**
     * Calculates comparator output from stack fullness across all container slots. An
     * empty layout or a layout containing only shared air sentinels returns {@code 0}.
     * Occupied slots contribute their count divided by their item type's maximum stack
     * size.
     *
     * <p>
     * The average includes empty slots in its denominator, then scales by {@code 14} and
     * adds one for a nonempty container. Legal stack counts therefore yield signals from
     * one through fifteen; this calculation does not clamp oversized or otherwise
     * invalid counts.
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
        // Reserve zero for empty containers and scale occupied fullness across the
        // remaining fourteen comparator signal levels.
        return (int) Math.floor(averageFullness * 14.0f) + 1;
    }
}
