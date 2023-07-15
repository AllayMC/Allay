package cn.allay.server.inventory;

import cn.allay.api.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import cn.allay.api.inventory.Inventory;
import cn.allay.api.inventory.InventoryHolder;
import cn.allay.api.inventory.InventoryViewer;
import cn.allay.api.item.ItemStack;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public abstract class BaseInventory implements Inventory {
    protected final Map<Integer, InventoryViewer> viewers = new Int2ObjectNonBlockingMap<>();
    protected final InventoryHolder holder;
    protected int size;
    protected AtomicReferenceArray<ItemStack> content;

    public BaseInventory(InventoryHolder holder, int size) {
        this.holder = holder;
        this.size = size;
        this.content = new AtomicReferenceArray<>(size);
    }

    @Override
    public InventoryHolder getHolder() {
        return holder;
    }

    @Override
    public Map<Integer, InventoryViewer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        return content.get(slot);
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack) {
        content.set(slot, itemStack);
    }

    @Override
    public void addViewer(InventoryViewer viewer) {
        viewers.put(viewer.assignInventoryId(), viewer);
    }

    @Override
    public InventoryViewer removeViewer(InventoryViewer viewer) {
        return viewers.remove(viewer.assignInventoryId());
    }

    @Override
    public InventoryViewer removeViewer(int viewerId) {
        return viewers.remove(viewerId);
    }
}
