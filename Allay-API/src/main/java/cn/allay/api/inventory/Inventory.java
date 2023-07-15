package cn.allay.api.inventory;

import cn.allay.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface Inventory {

    InventoryHolder getHolder();

    ContainerType getType();

    Map<Integer, InventoryViewer> getViewers();

    @Nullable
    ItemStack getItemStack(int slot);

    void setItemStack(int slot, ItemStack itemStack);

    void addViewer(InventoryViewer viewer);

    @Nullable
    InventoryViewer removeViewer(InventoryViewer viewer);

    @Nullable
    InventoryViewer removeViewer(int viewerId);
}
