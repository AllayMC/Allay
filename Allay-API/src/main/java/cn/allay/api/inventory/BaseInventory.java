package cn.allay.api.inventory;

import cn.allay.api.item.ItemStack;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public abstract class BaseInventory implements Inventory {
    protected final BiMap<Byte, InventoryViewer> viewers = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
    protected int size;
    protected ItemStack[] content;

    public BaseInventory(int size) {
        this.size = size;
        this.content = new ItemStack[size];
        Arrays.fill(this.content, AIR);
    }

    @Override
    public Map<Byte, InventoryViewer> getViewers() {
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

    @Override
    public List<ItemData> toNetworkItemData() {
        return Arrays.stream(content).map(ItemStack::toNetworkItemData).toList();
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack) {
        content[slot] = itemStack;
    }

    @Override
    public void addViewer(InventoryViewer viewer) {
        if (viewers.containsValue(viewer))
            throw new IllegalArgumentException("viewer already exists");
        var assignedId = viewer.assignInventoryId();
        if (viewers.containsKey(assignedId)) {
            removeViewer(viewers.get(assignedId));
        }
        viewers.put(assignedId, viewer);
        viewer.onOpen(assignedId, this);
    }

    @Override
    public void removeViewer(InventoryViewer viewer) {
        viewer.onClose(viewers.inverse().remove(viewer), this);
    }

    @Override
    public InventoryViewer removeViewer(byte assignedId) {
        var removed = viewers.remove(assignedId);
        if (removed != null) removed.onClose(assignedId, this);
        return removed;
    }
}
