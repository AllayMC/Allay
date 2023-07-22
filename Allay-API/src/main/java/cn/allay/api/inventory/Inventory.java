package cn.allay.api.inventory;

import cn.allay.api.data.VanillaItemTypes;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemStackInitInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface Inventory {

    ItemStack AIR = VanillaItemTypes.AIR_TYPE.createItemStack(new ItemStackInitInfo.Simple(1));

    ContainerType getType();

    Map<Byte, InventoryViewer> getViewers();

    ItemStack getItemStack(int slot);

    @UnmodifiableView
    List<ItemStack> getItemStacks();

    List<ItemData> toNetworkItemData();

    void setItemStack(int slot, ItemStack itemStack);

    void addViewer(InventoryViewer viewer);

    @Nullable
    void removeViewer(InventoryViewer viewer);

    @Nullable
    InventoryViewer removeViewer(byte viewerId);

    default void sendContents(InventoryViewer viewer) {
        viewer.sendContents(this);
    }

    default void sendContent(InventoryViewer viewer, int slot) {
        viewer.sendContent(this, slot);
    }
}
