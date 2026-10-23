package org.allaymc.server.container.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.eventbus.event.container.ContainerCloseEvent;
import org.allaymc.api.eventbus.event.container.ContainerOpenEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.player.Player;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

/**
 * {@code BaseContainer} stores mutable item stacks and manages viewer membership, slot
 * notifications, listeners, and item NBT for ordinary containers. Empty slots contain
 * the shared air sentinel, while assignments retain the supplied item stack. The array
 * accessor exposes backing storage and the list accessor exposes a live unmodifiable
 * list of the same stacks; owners coordinate access to this mutable state.
 *
 * <p>
 * Opening first dispatches a cancellable event, then opens the viewer and records its
 * assigned window ID before running open listeners. An ID collision removes the previous
 * viewer through the normal close path. Slot notifications send viewer updates before
 * listeners receive the current stored stack; suppressing those updates does not
 * suppress listeners. NBT loading replaces only supplied slots and bypasses
 * notifications.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BaseContainer implements Container {

    /**
     * {@code TAG_SLOT} names the byte-valued NBT field that preserves each serialized
     * item's container slot for loading.
     */
    protected static final String TAG_SLOT = "Slot";

    /**
     * Container identity defining the allocated slot count.
     */
    protected final ContainerType<? extends Container> containerType;
    /**
     * {@code viewers} associates assigned window IDs with registered viewers. The
     * inverse view supports removal by viewer, and an opening ID collision closes the
     * previous occupant.
     */
    protected final BiMap<Byte, ContainerViewer> viewers;
    /**
     * {@code content} is the mutable backing slot array returned directly by {@code
     * getItemStackArray()}. Assignments retain item references, and empty entries use
     * the shared air sentinel.
     */
    protected final ItemStack[] content;
    /**
     * {@code openListeners} holds callbacks run after viewer membership is recorded. Set
     * membership prevents equal listeners from being registered twice.
     */
    protected final Set<Consumer<ContainerViewer>> openListeners;
    /**
     * {@code closeListeners} holds callbacks run after membership removal and viewer
     * close notification. Equal registrations share a single set entry.
     */
    protected final Set<Consumer<ContainerViewer>> closeListeners;
    /**
     * {@code slotChangeListeners} groups callbacks by container slot. Notifications pass
     * the current stored stack after optional viewer updates, even when {@code send} is
     * {@code false}.
     */
    protected final Int2ObjectMap<Set<Consumer<ItemStack>>> slotChangeListeners;

    /**
     * Creates empty item storage sized by the supplied container type. Every slot starts
     * with the shared air sentinel, and viewer and listener collections begin empty for
     * subsequent registrations.
     */
    public BaseContainer(ContainerType<? extends Container> containerType) {
        this.containerType = containerType;
        this.viewers = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.content = new ItemStack[containerType.getSize()];
        this.openListeners = new HashSet<>();
        this.closeListeners = new HashSet<>();
        this.slotChangeListeners = new Int2ObjectOpenHashMap<>();
        Arrays.fill(this.content, ItemAirStack.AIR_STACK);
    }

    /**
     * Returns the container identity used to allocate this item storage.
     */
    @Override
    public ContainerType<?> getContainerType() {
        return containerType;
    }

    /**
     * Returns an unmodifiable live view of the assigned-ID viewer map. Callers observe
     * later membership changes through the same view, while registration remains
     * controlled by the container's opening and removal operations.
     */
    @Override
    public Map<Byte, ContainerViewer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    /**
     * Returns the stored stack at the supplied zero-based index. The returned reference
     * is shared with {@code content}; callers must notify the slot after directly
     * mutating its item data.
     */
    @Override
    public ItemStack getItemStack(int slot) {
        return content[slot];
    }

    /**
     * Returns an unmodifiable list backed by {@code content}. Later slot assignments
     * remain visible, and the stack objects remain shared and mutable despite the list's
     * prohibition on structural or element replacement.
     */
    @UnmodifiableView
    @Override
    public List<ItemStack> getItemStacks() {
        return Collections.unmodifiableList(Arrays.asList(content));
    }

    /**
     * Returns the actual backing slot array despite its view annotation. Array changes
     * bypass {@code null} checks and notifications, so callers should use slot setters and
     * notification operations for container updates.
     */
    @UnmodifiableView
    @Override
    public ItemStack[] getItemStackArray() {
        return content;
    }

    /**
     * Stores the supplied stack reference and then notifies that slot. {@code null}
     * throws {@code NullPointerException} before assignment; {@code send} controls
     * ordinary viewer updates while listeners still receive the stored stack.
     */
    @Override
    public void setItemStack(int slot, ItemStack itemStack, boolean send) {
        Preconditions.checkNotNull(itemStack, "Passing null value to Container#setItemStack is not allowed!");
        content[slot] = itemStack;
        notifySlotChange(slot, send);
    }

    /**
     * Opens this container for a viewer and records its assigned window ID. An existing
     * membership returns {@code true} immediately; forbidden player block-container
     * access or a cancelled opening event returns {@code false} before opening.
     *
     * <p>
     * The viewer opens before membership is installed. If its ID is already occupied,
     * the previous viewer is removed through the normal close path before replacement.
     * Open listeners run after insertion, so an exception in a viewer callback or
     * listener can interrupt this sequence without rolling back earlier effects.
     */
    @Override
    public boolean addViewer(ContainerViewer viewer) {
        if (viewers.containsValue(viewer)) {
            return true;
        }
        if (viewer instanceof Player player && this instanceof BlockContainer && !player.canOpenContainers()) {
            return false;
        }

        var event = new ContainerOpenEvent(viewer, this);
        if (!event.call()) {
            return false;
        }

        // Open the viewer before registering its assigned ID; a colliding ID closes
        // the earlier member before the replacement receives open listeners.
        var assignedId = viewer.viewContainerOpen(this);
        if (viewers.containsKey(assignedId)) {
            removeViewer(viewers.get(assignedId));
        }
        viewers.put(assignedId, viewer);
        onOpen(viewer);

        return true;
    }

    /**
     * Removes a registered viewer after dispatching the close event. An absent
     * membership returns {@code false}; a removed membership is closed on the viewer
     * before close listeners run and {@code true} returns.
     */
    @Override
    public boolean removeViewer(ContainerViewer viewer) {
        new ContainerCloseEvent(viewer, this).call();

        // Remove membership before viewer and listener callbacks so those callbacks
        // observe the closed membership even if they fail.
        var removed = viewers.inverse().remove(viewer);
        if (removed != null) {
            viewer.viewContainerClose(this);
            onClose(viewer);
            return true;
        }

        return false;
    }

    /**
     * Publishes the current stack for one slot to its observers. When {@code send} is
     * {@code true}, viewer updates precede listener callbacks; listeners receive the
     * stored reference even when viewer updates are suppressed.
     */
    @Override
    public void notifySlotChange(int slot, boolean send) {
        if (send) {
            for (var viewer : viewers.values()) {
                viewer.viewContainerSlot(this, slot);
            }
        }

        var listeners = slotChangeListeners.get(slot);
        if (listeners == null || listeners.isEmpty()) {
            return;
        }

        for (var listener : listeners) {
            listener.accept(content[slot]);
        }
    }

    /**
     * Invokes the registered open listeners after viewer membership is installed.
     * Callbacks run directly in set iteration order, and an exception stops iteration
     * without removing the established membership.
     */
    protected void onOpen(ContainerViewer viewer) {
        openListeners.forEach(listener -> listener.accept(viewer));
    }

    /**
     * Invokes the registered close listeners after the viewer close callback. Membership
     * has already been removed, so a listener failure does not restore that viewer or
     * resume remaining callbacks.
     */
    protected void onClose(ContainerViewer viewer) {
        closeListeners.forEach(listener -> listener.accept(viewer));
    }

    /**
     * Registers a callback in this container's open listener set.
     */
    @Override
    public void addOpenListener(Consumer<ContainerViewer> listener) {
        openListeners.add(listener);
    }

    /**
     * Removes a callback from this container's open listener set.
     */
    @Override
    public void removeOpenListener(Consumer<ContainerViewer> listener) {
        openListeners.remove(listener);
    }

    /**
     * Registers a callback in this container's close listener set.
     */
    @Override
    public void addCloseListener(Consumer<ContainerViewer> listener) {
        closeListeners.add(listener);
    }

    /**
     * Removes a callback from this container's close listener set.
     */
    @Override
    public void removeCloseListener(Consumer<ContainerViewer> listener) {
        closeListeners.remove(listener);
    }

    /**
     * Registers a callback in the listener set for one slot.
     */
    @Override
    public void addSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        slotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).add(listener);
    }

    /**
     * Removes a callback from the listener set for one slot.
     */
    @Override
    public void removeSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        slotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).remove(listener);
    }

    /**
     * Serializes stored stacks with byte-valued slot indices for subsequent loading.
     * {@code saveEmptySlots} includes air entries when {@code true}; otherwise only
     * occupied entries are saved. Slots above {@code 127} lose their nonnegative index
     * on signed-byte loading and may address invalid or wrapped slots.
     */
    @Override
    public List<NbtMap> saveNBT(boolean saveEmptySlots) {
        var list = new ArrayList<NbtMap>(content.length);
        for (int slot = 0; slot < content.length; slot++) {
            if (!saveEmptySlots && isEmpty(slot)) {
                continue;
            }

            var itemStack = content[slot];
            var nbt = itemStack.saveNBT()
                    .toBuilder()
                    // The byte cast truncates large indices; loadNBT sign-extends
                    // this byte, so indices above 127 cannot round-trip as the
                    // original nonnegative slot.
                    .putByte(TAG_SLOT, (byte) slot)
                    .build();
            list.add(nbt);
        }
        return new NbtList<>(NbtType.COMPOUND, list);
    }

    /**
     * Loads entries into their serialized slots without clearing other content or
     * sending notifications. Entries lacking {@code TAG_SLOT} are logged and skipped;
     * invalid slot indices can fail after earlier entries have already replaced storage.
     */
    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        for (var nbt : nbtList) {
            // Entries without a slot cannot identify storage; retain other content
            // and continue loading the remaining addressable item entries.
            if (!nbt.containsKey(TAG_SLOT)) {
                log.warn("Item NBT does not contain a slot key! Skipping item...");
                continue;
            }
            int slot = nbt.getByte(TAG_SLOT);
            ItemStack itemStack = NBTIO.getAPI().fromItemStackNBT(nbt);
            content[slot] = itemStack;
        }
    }
}
