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
import org.cloudburstmc.nbt.NbtMap;

import java.util.*;
import java.util.function.Consumer;

/**
 * A virtual container that combines two adjacent chest containers into a single double chest view.
 * <p>
 * This class does not store items directly, but delegates to the underlying left and right
 * chest containers. It manages viewer synchronization to ensure players see updates when
 * either chest is modified (e.g., by hoppers).
 *
 * @author IWareQ | daoge_cmd | ClexaGod
 */
@Slf4j
public class DoubleChestContainerImpl implements BlockContainer {

    protected static final int CHEST_SIZE = ContainerTypes.CHEST.getSize();

    protected final BiMap<Byte, ContainerViewer> viewers;

    // Listeners triggered when the container is opened/closed
    protected final Set<Consumer<ContainerViewer>> openListeners;
    protected final Set<Consumer<ContainerViewer>> closeListeners;

    // External slot change listeners registered via API
    protected final Int2ObjectMap<Set<Consumer<ItemStack>>> leftSlotChangeListeners;
    protected final Int2ObjectMap<Set<Consumer<ItemStack>>> rightSlotChangeListeners;

    // Listeners that sync underlying container changes to double chest viewers
    protected final Int2ObjectMap<Consumer<ItemStack>> leftSyncListeners;
    protected final Int2ObjectMap<Consumer<ItemStack>> rightSyncListeners;

    @Getter
    @Setter
    protected Position3ic blockPos;
    protected Container left;
    protected Container right;

    public DoubleChestContainerImpl() {
        this.viewers = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.openListeners = new HashSet<>();
        this.closeListeners = new HashSet<>();
        this.leftSlotChangeListeners = new Int2ObjectOpenHashMap<>();
        this.rightSlotChangeListeners = new Int2ObjectOpenHashMap<>();
        this.leftSyncListeners = new Int2ObjectOpenHashMap<>();
        this.rightSyncListeners = new Int2ObjectOpenHashMap<>();
    }

    private static boolean isLeft(int slot) {
        return slot < CHEST_SIZE;
    }

    @Override
    public ContainerType<?> getContainerType() {
        return ContainerTypes.DOUBLE_CHEST;
    }

    public void setLeft(Container left) {
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

    public void setRight(Container right) {
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

    @Override
    public void addOpenListener(Consumer<ContainerViewer> listener) {
        openListeners.add(listener);
    }

    @Override
    public void removeOpenListener(Consumer<ContainerViewer> listener) {
        openListeners.remove(listener);
    }

    @Override
    public void addCloseListener(Consumer<ContainerViewer> listener) {
        closeListeners.add(listener);
    }

    @Override
    public void removeCloseListener(Consumer<ContainerViewer> listener) {
        closeListeners.remove(listener);
    }

    @Override
    public Map<Byte, ContainerViewer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        if (isLeft(slot)) {
            return left.getItemStack(slot);
        }

        return right.getItemStack(slot - CHEST_SIZE);
    }

    @Override
    public List<ItemStack> getItemStacks() {
        var list = new ArrayList<ItemStack>();
        list.addAll(left.getItemStacks());
        list.addAll(right.getItemStacks());
        return list;
    }

    @Override
    public ItemStack[] getItemStackArray() {
        return getItemStacks().toArray(ItemStack[]::new);
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack, boolean send) {
        if (isLeft(slot)) {
            left.setItemStack(slot, itemStack, send);
            return;
        }

        right.setItemStack(slot - CHEST_SIZE, itemStack, send);
    }

    @Override
    public boolean addViewer(ContainerViewer viewer) {
        if (viewers.containsValue(viewer)) {
            log.warn("Viewer already exists! Container: {}, Viewer: {}", getContainerType(), viewer);
            removeViewer(viewer);
            return addViewer(viewer);
        }

        var event = new ContainerOpenEvent(viewer, this);
        if (!event.call()) {
            return false;
        }

        var assignedId = viewer.viewContainerOpen(this);
        if (viewers.containsKey(assignedId)) {
            removeViewer(viewers.get(assignedId));
        }
        viewers.put(assignedId, viewer);
        onOpen(viewer);

        return true;
    }

    @Override
    public boolean removeViewer(ContainerViewer viewer) {
        new ContainerCloseEvent(viewer, this).call();

        var removed = viewers.inverse().remove(viewer);
        if (removed != null) {
            viewer.viewContainerClose(this);
            onClose(viewer);
            return true;
        }

        return false;
    }

    protected void onOpen(ContainerViewer viewer) {
        openListeners.forEach(listener -> listener.accept(viewer));
    }

    protected void onClose(ContainerViewer viewer) {
        closeListeners.forEach(listener -> listener.accept(viewer));
    }

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

    @Override
    public List<NbtMap> saveNBT() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        throw new UnsupportedOperationException();
    }

    private void attachSyncListeners(Container container, Int2ObjectMap<Consumer<ItemStack>> listeners, int slotOffset) {
        for (int slot = 0; slot < CHEST_SIZE; slot++) {
            int viewSlot = slot + slotOffset;
            Consumer<ItemStack> listener = $ -> viewers.values().forEach(viewer -> viewer.viewContainerSlot(this, viewSlot));
            listeners.put(slot, listener);
            container.addSlotChangeListener(slot, listener);
        }
    }

    private void detachSyncListeners(Container container, Int2ObjectMap<Consumer<ItemStack>> listeners) {
        listeners.forEach(container::removeSlotChangeListener);
        listeners.clear();
    }
}
