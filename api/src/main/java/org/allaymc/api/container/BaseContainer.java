package org.allaymc.api.container;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.container.ContainerCloseEvent;
import org.allaymc.api.eventbus.event.container.ContainerOpenEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

import static org.allaymc.api.item.ItemHelper.fromNBT;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BaseContainer implements Container {

    protected static final String TAG_SLOT = "Slot";

    protected final FullContainerType<? extends Container> containerType;
    protected final BiMap<Byte, ContainerViewer> viewers;
    protected final ItemStack[] content;
    protected final Set<Consumer<ContainerViewer>> onOpenListeners;
    protected final Set<Consumer<ContainerViewer>> onCloseListeners;
    protected final Int2ObjectMap<Set<Consumer<ItemStack>>> onSlotChangeListeners;

    public BaseContainer(FullContainerType<? extends Container> containerType) {
        this.containerType = containerType;
        this.viewers = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.content = new ItemStack[containerType.size()];
        this.onOpenListeners = new HashSet<>();
        this.onCloseListeners = new HashSet<>();
        this.onSlotChangeListeners = new Int2ObjectOpenHashMap<>();
        Arrays.fill(this.content, ItemAirStack.AIR_STACK);
    }

    @Override
    public FullContainerType<?> getContainerType() {
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
    public List<ItemData> toNetworkItemData() {
        return Arrays.stream(content).map(ItemStack::toNetworkItemData).toList();
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack, boolean send) {
        Preconditions.checkNotNull(itemStack, "Passing null value to Container#setItemStack is not allowed!");
        content[slot] = itemStack;
        notifySlotChange(slot, send);
    }

    @Override
    public void addViewer(ContainerViewer viewer) {
        var event = new ContainerOpenEvent(viewer, this);
        if (!event.call()) return;

        if (viewers.containsValue(viewer)) {
            log.warn("Viewer already exists! Container: {}, Viewer: {}", this.containerType, viewer);
            removeViewer(viewer);
            addViewer(viewer);
            return;
        }
        var assignedId = viewer.assignContainerId();
        if (viewers.containsKey(assignedId)) {
            removeViewer(viewers.get(assignedId));
        }
        viewers.put(assignedId, viewer);
        viewer.onOpen(assignedId, this);
        onOpen(viewer);
    }

    @Override
    public void removeViewer(ContainerViewer viewer) {
        var event = new ContainerCloseEvent(viewer, this);
        if (!event.call()) return;

        viewer.onClose(viewers.inverse().remove(viewer), this);
        onClose(viewer);
    }

    @Override
    public ContainerViewer removeViewer(byte assignedId) {
        var removed = viewers.remove(assignedId);
        if (removed != null) removed.onClose(assignedId, this);
        return removed;
    }

    @Override
    public void notifySlotChange(int slot, boolean send) {
        if (send) {
            for (var viewer : viewers.values()) {
                viewer.notifySlotChange(this, slot);
            }
        }

        var listeners = onSlotChangeListeners.get(slot);
        if (listeners == null || listeners.isEmpty()) {
            return;
        }

        for (var listener : listeners) {
            listener.accept(content[slot]);
        }
    }

    @Override
    public void onOpen(ContainerViewer viewer) {
        onOpenListeners.forEach(listener -> listener.accept(viewer));
    }

    @Override
    public void onClose(ContainerViewer viewer) {
        onCloseListeners.forEach(listener -> listener.accept(viewer));
    }

    @Override
    public void addOnOpenListener(Consumer<ContainerViewer> listener) {
        onOpenListeners.add(listener);
    }

    @Override
    public void removeOnOpenListener(Consumer<ContainerViewer> listener) {
        onOpenListeners.remove(listener);
    }

    @Override
    public void addOnCloseListener(Consumer<ContainerViewer> listener) {
        onCloseListeners.add(listener);
    }

    @Override
    public void removeOnCloseListener(Consumer<ContainerViewer> listener) {
        onCloseListeners.remove(listener);
    }

    @Override
    public void addOnSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        onSlotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).add(listener);
    }

    @Override
    public void removeOnSlotChangeListener(int slot, Consumer<ItemStack> listener) {
        onSlotChangeListeners.computeIfAbsent(slot, k -> new HashSet<>()).remove(listener);
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
            ItemStack itemStack = fromNBT(nbt);
            content[slot] = itemStack;
        }
    }
}
