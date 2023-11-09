package org.allaymc.api.container;

import org.allaymc.api.container.exception.ContainerException;
import org.allaymc.api.item.ItemStack;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

import static org.allaymc.api.item.ItemHelper.fromNBT;
import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_TYPE;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
@Slf4j
public class BaseContainer implements Container {
    protected final FullContainerType<? extends Container> containerType;
    protected final BiMap<Byte, ContainerViewer> viewers = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
    protected final ItemStack[] content;
    protected final Set<Consumer<ContainerViewer>> onOpenListeners = new HashSet<>();
    protected final Set<Consumer<ContainerViewer>> onCloseListeners = new HashSet<>();

    public BaseContainer(FullContainerType<? extends Container> containerType) {
        this.containerType = containerType;
        this.content = new ItemStack[containerType.size()];
        Arrays.fill(this.content, EMPTY_SLOT_PLACE_HOLDER);
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
    public void setItemStack(int slot, ItemStack itemStack) {
        if (itemStack.getItemType() == AIR_TYPE && itemStack != EMPTY_SLOT_PLACE_HOLDER) {
            // NOTICE: Please use clearSlot() instead of using this method if you want to clear a slot!
            itemStack = EMPTY_SLOT_PLACE_HOLDER;
        }
        content[slot] = itemStack;
        onSlotChange(slot);
    }

    @Override
    public void addViewer(ContainerViewer viewer) {
        if (viewers.containsValue(viewer))
            throw new ContainerException("viewer already exists");
        var assignedId = viewer.assignInventoryId();
        if (viewers.containsKey(assignedId)) {
            removeViewer(viewers.get(assignedId));
        }
        viewers.put(assignedId, viewer);
        viewer.onOpen(assignedId, this);
        onOpen(viewer);
    }

    @Override
    public void removeViewer(ContainerViewer viewer) {
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
    public void onSlotChange(int slot) {
        for (var viewer : viewers.values()) {
            viewer.onSlotChange(this, slot);
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
    public NbtList<NbtMap> saveNBT() {
        var list = new ArrayList<NbtMap>(content.length);
        for (int slot = 0; slot < content.length; slot++) {
            var itemStack = content[slot];
            //TODO: WasPickedUp?
            var nbt = itemStack.saveNBT()
                    .toBuilder()
                    .putByte("Slot", (byte) slot)
                    .build();
            list.add(slot, nbt);
        }
        return new NbtList<>(NbtType.COMPOUND, list);
    }

    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        for (var nbt : nbtList) {
            int slot = nbt.getByte("Slot");
            try {
                ItemStack itemStack = fromNBT(nbt);
                content[slot] = itemStack;
            } catch (NullPointerException e) {
                log.error("An error happen while loading container items", e);
            }
        }
    }
}
