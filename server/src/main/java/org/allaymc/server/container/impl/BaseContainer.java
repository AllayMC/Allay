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
import org.allaymc.api.eventbus.event.container.ContainerCloseEvent;
import org.allaymc.api.eventbus.event.container.ContainerOpenEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BaseContainer implements Container {

    protected static final String TAG_SLOT = "Slot";

    protected final ContainerType<? extends Container> containerType;
    protected final BiMap<Byte, ContainerViewer> viewers;
    protected final ItemStack[] content;
    protected final Set<Consumer<ContainerViewer>> openListeners;
    protected final Set<Consumer<ContainerViewer>> closeListeners;
    protected final Int2ObjectMap<Set<Consumer<ItemStack>>> slotChangeListeners;

    public BaseContainer(ContainerType<? extends Container> containerType) {
        this.containerType = containerType;
        this.viewers = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.content = new ItemStack[containerType.getSize()];
        this.openListeners = new HashSet<>();
        this.closeListeners = new HashSet<>();
        this.slotChangeListeners = new Int2ObjectOpenHashMap<>();
        Arrays.fill(this.content, ItemAirStack.AIR_STACK);
    }

    @Override
    public ContainerType<?> getContainerType() {
        return containerType;
    }

    @Override
    public Map<Byte, ContainerViewer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        return content[slot];
    }

    @UnmodifiableView
    @Override
    public List<ItemStack> getItemStacks() {
        return Collections.unmodifiableList(Arrays.asList(content));
    }

    @UnmodifiableView
    @Override
    public ItemStack[] getItemStackArray() {
        return content;
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack, boolean send) {
        Preconditions.checkNotNull(itemStack, "Passing null value to Container#setItemStack is not allowed!");
        content[slot] = itemStack;
        notifySlotChange(slot, send);
    }

    @Override
    public boolean addViewer(ContainerViewer viewer) {
        if (viewers.containsValue(viewer)) {
            log.warn("Viewer already exists! Container: {}, Viewer: {}", this.containerType, viewer);
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

    protected void onOpen(ContainerViewer viewer) {
        openListeners.forEach(listener -> listener.accept(viewer));
    }

    protected void onClose(ContainerViewer viewer) {
        closeListeners.forEach(listener -> listener.accept(viewer));
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
    public void addSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        slotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).add(listener);
    }

    @Override
    public void removeSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        slotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).remove(listener);
    }

    @Override
    public List<NbtMap> saveNBT() {
        var list = new ArrayList<NbtMap>(content.length);
        for (int slot = 0; slot < content.length; slot++) {
            if (isEmpty(slot)) continue;
            var itemStack = content[slot];
            // TODO: WasPickedUp?
            var nbt = itemStack.saveNBT()
                    .toBuilder()
                    .putByte(TAG_SLOT, (byte) slot)
                    .build();
            list.add(nbt);
        }
        return new NbtList<>(NbtType.COMPOUND, list);
    }

    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        for (var nbt : nbtList) {
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
