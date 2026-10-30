package org.allaymc.server.container.impl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.eventbus.event.container.ContainerCloseEvent;
import org.allaymc.api.eventbus.event.container.ContainerOpenEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.player.Player;
import org.cloudburstmc.nbt.NbtMap;

import java.util.*;
import java.util.function.Consumer;

/**
 * {@code DoubleChestContainerImpl} presents two single-chest inventories as one
 * fifty-four-slot container without owning separate item storage. Left indices address
 * the first chest directly, while right indices subtract the single-chest size. The
 * owner configures both halves and their lead block position before opening the combined
 * interface.
 *
 * <p>
 * The combined window has its own viewer map and open/close listeners. External slot
 * listeners attach to the underlying half and migrate when that half is replaced.
 * Additional synchronization listeners translate underlying changes back to combined
 * indices, so direct chest changes reach combined viewers even when ordinary underlying
 * viewer sending is suppressed. Item list and array access copies only their structure;
 * the contained stacks remain shared with the two chests.
 *
 * @author IWareQ | daoge_cmd | ClexaGod
 */
@Slf4j
public class DoubleChestContainerImpl implements BlockContainer {

    /**
     * {@code CHEST_SIZE} is the single-chest slot count used as the boundary and
     * right-half index offset throughout the combined view.
     */
    protected static final int CHEST_SIZE = ContainerTypes.CHEST.getSize();

    /**
     * {@code viewers} holds only the combined window's memberships. Underlying chest
     * viewers remain separate, and an assigned-ID collision closes the previous combined
     * viewer.
     */
    protected final BiMap<Byte, ContainerViewer> viewers;

    // openListeners and closeListeners observe this combined window only; opening the
    // combined view does not register viewers with either underlying chest.
    /**
     * Combined-window open callback registrations.
     */
    protected final Set<Consumer<ContainerViewer>> openListeners;
    /**
     * Combined-window close callback registrations.
     */
    protected final Set<Consumer<ContainerViewer>> closeListeners;

    // leftSlotChangeListeners and rightSlotChangeListeners retain external
    // registrations using each half's local indices, so replacement halves can
    // inherit the original API callbacks.
    /**
     * External left-half slot callback registrations.
     */
    protected final Int2ObjectMap<Set<Consumer<ItemStack>>> leftSlotChangeListeners;
    /**
     * External right-half local-slot callback registrations.
     */
    protected final Int2ObjectMap<Set<Consumer<ItemStack>>> rightSlotChangeListeners;

    // leftSyncListeners and rightSyncListeners retain callbacks attached to their
    // respective halves; replacing a half detaches its old callbacks before
    // installing new synchronization.
    /**
     * Left-half combined-window synchronization callback registrations.
     */
    protected final Int2ObjectMap<Consumer<ItemStack>> leftSyncListeners;
    /**
     * Right-half combined-window synchronization callback registrations.
     */
    protected final Int2ObjectMap<Consumer<ItemStack>> rightSyncListeners;

    /**
     * {@code blockPos} is the position used to open the combined window. The chest owner
     * sets it after configuring both underlying containers.
     */
    @Getter
    @Setter
    protected Position3ic blockPos;
    // left and right supply the same fixed-size storage layout. The owner configures
    // both halves before storage access, while replacements migrate their recorded
    // listeners.
    /**
     * Storage supplying the first chest half.
     */
    protected Container left;
    /**
     * Storage supplying the second chest half.
     */
    protected Container right;

    /**
     * Creates an unconfigured double-chest view with empty viewer and listener
     * collections. The owner must configure both chest halves and the block position
     * before using storage or opening its window.
     */
    public DoubleChestContainerImpl() {
        this.viewers = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.openListeners = new HashSet<>();
        this.closeListeners = new HashSet<>();
        this.leftSlotChangeListeners = new Int2ObjectOpenHashMap<>();
        this.rightSlotChangeListeners = new Int2ObjectOpenHashMap<>();
        this.leftSyncListeners = new Int2ObjectOpenHashMap<>();
        this.rightSyncListeners = new Int2ObjectOpenHashMap<>();
    }

    /**
     * Returns whether the combined slot precedes the right-half boundary.
     */
    private static boolean isLeft(int slot) {
        return slot < CHEST_SIZE;
    }

    /**
     * Returns the shared identity for the fifty-four-slot double-chest layout.
     */
    @Override
    public ContainerType<?> getContainerType() {
        return ContainerTypes.DOUBLE_CHEST;
    }

    /**
     * Assigns the storage backing the first chest half and migrates its listeners. Both
     * storage halves must use the single-chest layout expected by the fixed slot
     * boundary.
     *
     * <p>
     * When an old half exists, external callbacks move from that half to the replacement
     * before old synchronization callbacks detach. New synchronization callbacks then
     * publish replacement-half changes to combined viewers. A replacement must be
     * non-{@code null} when external callbacks are being migrated; callback registration
     * failures can leave this migration partially applied.
     */
    public void setLeft(Container left) {
        // Migrate external callbacks before replacing ownership, then detach old
        // synchronization so later changes from that half no longer update combined
        // viewers.
        if (this.left != null) {
            this.leftSlotChangeListeners.forEach((slot, set) -> set.forEach(listener -> {
                this.left.removeSlotChangeListener(slot, listener);
                left.addSlotChangeListener(slot, listener);
            }));
            detachSyncListeners(this.left, leftSyncListeners);
        }
        this.left = left;
        if (this.left != null) {
            attachSyncListeners(left, leftSyncListeners, 0);
        }
    }

    /**
     * Assigns the storage backing the second chest half and migrates its listeners. Both
     * storage halves must use the single-chest layout expected by the fixed slot
     * boundary.
     *
     * <p>
     * External right-half registrations use local indices in the underlying container.
     * They migrate before the old synchronization callbacks detach; replacement
     * synchronization callbacks add {@code CHEST_SIZE} to report combined window
     * indices. A replacement must be non-{@code null} when external callbacks are
     * migrated, and a registration failure can leave partial migration.
     */
    public void setRight(Container right) {
        // Migrate external callbacks using local right-half indices before detaching
        // synchronization; replacement synchronization applies the combined-window
        // offset when it is attached.
        if (this.right != null) {
            this.rightSlotChangeListeners.forEach((slot, set) -> set.forEach(listener -> {
                this.right.removeSlotChangeListener(slot, listener);
                right.addSlotChangeListener(slot, listener);
            }));
            detachSyncListeners(this.right, rightSyncListeners);
        }
        this.right = right;
        if (this.right != null) {
            attachSyncListeners(right, rightSyncListeners, CHEST_SIZE);
        }
    }

    /**
     * Registers an external callback on the half owning the combined slot index.
     * Right-half indices are translated to local indices, and the registration is
     * retained for migration when that half changes.
     */
    @Override
    public void addSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        if (isLeft(slot)) {
            this.leftSlotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).add(listener);
            this.left.addSlotChangeListener(slot, listener);
            return;
        }

        slot = slot - CHEST_SIZE;
        this.rightSlotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).add(listener);
        this.right.addSlotChangeListener(slot, listener);
    }

    /**
     * Removes an external callback from the current half owning the combined slot index.
     * The retained migration registration is removed as well, using a local index for
     * right-half slots.
     */
    @Override
    public void removeSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        if (isLeft(slot)) {
            this.leftSlotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).remove(listener);
            this.left.removeSlotChangeListener(slot, listener);
            return;
        }

        slot = slot - CHEST_SIZE;
        this.rightSlotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).remove(listener);
        this.right.removeSlotChangeListener(slot, listener);
    }

    /**
     * Registers a callback for successful combined-window viewer opening.
     */
    @Override
    public void addOpenListener(Consumer<ContainerViewer> listener) {
        openListeners.add(listener);
    }

    /**
     * Removes a callback from the combined-window open listener set.
     */
    @Override
    public void removeOpenListener(Consumer<ContainerViewer> listener) {
        openListeners.remove(listener);
    }

    /**
     * Registers a callback for successful combined-window viewer removal.
     */
    @Override
    public void addCloseListener(Consumer<ContainerViewer> listener) {
        closeListeners.add(listener);
    }

    /**
     * Removes a callback from the combined-window close listener set.
     */
    @Override
    public void removeCloseListener(Consumer<ContainerViewer> listener) {
        closeListeners.remove(listener);
    }

    /**
     * Returns an unmodifiable live view of combined-window viewers by assigned ID.
     * Underlying chest viewers are absent from this map, and subsequent combined
     * membership changes remain visible.
     */
    @Override
    public Map<Byte, ContainerViewer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    /**
     * Returns the underlying stack addressed by a combined slot index. Right-half
     * indices subtract {@code CHEST_SIZE}; the result remains shared with its chest, so
     * direct mutations require notification.
     */
    @Override
    public ItemStack getItemStack(int slot) {
        if (isLeft(slot)) {
            return left.getItemStack(slot);
        }

        return right.getItemStack(slot - CHEST_SIZE);
    }

    /**
     * Returns a new mutable list containing left stacks followed by right stacks. The
     * list structure is separate, but every element remains the corresponding underlying
     * chest's shared stack reference.
     */
    @Override
    public List<ItemStack> getItemStacks() {
        var list = new ArrayList<ItemStack>();
        list.addAll(left.getItemStacks());
        list.addAll(right.getItemStacks());
        return list;
    }

    /**
     * Returns a new array containing left stacks followed by right stacks. Replacing
     * array entries does not replace chest slots, while mutating the shared stack
     * elements still affects underlying storage.
     */
    @Override
    public ItemStack[] getItemStackArray() {
        return getItemStacks().toArray(ItemStack[]::new);
    }

    /**
     * Assigns the stack through the owning chest's setter after translating right-half
     * indices. Underlying notifications invoke synchronization listeners, so combined
     * viewers can receive updates even when {@code send} is {@code false}.
     */
    @Override
    public void setItemStack(int slot, ItemStack itemStack, boolean send) {
        if (isLeft(slot)) {
            left.setItemStack(slot, itemStack, send);
            return;
        }

        right.setItemStack(slot - CHEST_SIZE, itemStack, send);
    }

    /**
     * Opens the combined window and records its assigned viewer ID. Existing membership
     * returns {@code true}; forbidden player access or a cancelled opening event returns
     * {@code false} before opening.
     *
     * <p>
     * The viewer callback opens before its membership is recorded. A colliding ID closes
     * the previous combined viewer before replacement, and open listeners run after the
     * new mapping is installed. Exceptions can interrupt this sequence after earlier
     * effects without rolling them back.
     */
    @Override
    public boolean addViewer(ContainerViewer viewer) {
        if (viewers.containsValue(viewer)) {
            return true;
        }
        if (viewer instanceof Player player && !player.canOpenContainers()) {
            return false;
        }

        var event = new ContainerOpenEvent(viewer, this);
        if (!event.call()) {
            return false;
        }

        // Open the viewer before recording its assigned ID; close any earlier member
        // holding that ID before replacement listeners observe the new membership.
        var assignedId = viewer.viewContainerOpen(this);
        if (viewers.containsKey(assignedId)) {
            removeViewer(viewers.get(assignedId));
        }
        viewers.put(assignedId, viewer);
        onOpen(viewer);

        return true;
    }

    /**
     * Dispatches the close event and removes the viewer from combined-window membership.
     * Absent membership returns {@code false}; otherwise the viewer closes before close
     * listeners run and {@code true} returns.
     */
    @Override
    public boolean removeViewer(ContainerViewer viewer) {
        new ContainerCloseEvent(viewer, this).call();

        // Remove membership before callbacks so viewer and close listeners observe
        // removal even when a later callback interrupts this close sequence.
        var removed = viewers.inverse().remove(viewer);
        if (removed != null) {
            viewer.viewContainerClose(this);
            onClose(viewer);
            return true;
        }

        return false;
    }

    /**
     * Invokes combined-window open listeners after membership has been installed.
     * Callbacks run directly from the registration set, so a failure stops subsequent
     * callbacks without removing the established viewer.
     */
    protected void onOpen(ContainerViewer viewer) {
        openListeners.forEach(listener -> listener.accept(viewer));
    }

    /**
     * Invokes combined-window close listeners after viewer closure and membership
     * removal. A listener failure does not restore membership or resume callbacks that
     * have not yet run.
     */
    protected void onClose(ContainerViewer viewer) {
        closeListeners.forEach(listener -> listener.accept(viewer));
    }

    /**
     * Publishes an optional combined-window update and forwards notification to the
     * selected underlying chest. The right-half branch currently forwards the combined
     * index unchanged, which can miss local listeners or fail during underlying viewer
     * synchronization.
     */
    @Override
    public void notifySlotChange(int slot, boolean send) {
        if (send) {
            for (var viewer : viewers.values()) {
                viewer.viewContainerSlot(this, slot);
            }
        }

        if (isLeft(slot)) {
            left.notifySlotChange(slot, send);
        } else {
            right.notifySlotChange(slot, send);
        }
    }

    /**
     * Rejects serialization of the combined virtual container with {@code
     * UnsupportedOperationException}. Owners persist the two underlying chests
     * individually; this view does not own a separate item storage representation to
     * save.
     */
    @Override
    public List<NbtMap> saveNBT(boolean saveEmptySlots) {
        throw new UnsupportedOperationException();
    }

    /**
     * Rejects loading into the combined virtual container with {@code
     * UnsupportedOperationException}. Owners load each underlying chest individually
     * rather than supply combined slot data to this virtual view.
     */
    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        throw new UnsupportedOperationException();
    }

    /**
     * Attaches one callback per chest slot to publish combined-window indices using
     * {@code slotOffset}. These callbacks run as underlying slot listeners, so
     * underlying notifications invoke them even when ordinary viewer sending is
     * suppressed.
     */
    private void attachSyncListeners(Container container, Int2ObjectMap<Consumer<ItemStack>> listeners, int slotOffset) {
        for (int slot = 0; slot < CHEST_SIZE; slot++) {
            // Underlying notifications use local indices; capture the translated
            // combined index so either chest reports changes to the correct viewer
            // slot.
            int viewSlot = slot + slotOffset;
            Consumer<ItemStack> listener = $ -> viewers.values().forEach(viewer -> viewer.viewContainerSlot(this, viewSlot));
            listeners.put(slot, listener);
            container.addSlotChangeListener(slot, listener);
        }
    }

    /**
     * Removes every recorded synchronization callback from its underlying chest and then
     * clears the callback registry. Owners call this before attaching the replacement
     * half's synchronization callbacks.
     */
    private void detachSyncListeners(Container container, Int2ObjectMap<Consumer<ItemStack>> listeners) {
        listeners.forEach(container::removeSlotChangeListener);
        listeners.clear();
    }
}
