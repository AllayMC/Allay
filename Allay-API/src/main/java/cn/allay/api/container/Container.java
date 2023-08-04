package cn.allay.api.container;

import cn.allay.api.data.VanillaItemTypes;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemStackInitInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
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
public interface Container {

    ItemStack AIR_STACK = VanillaItemTypes.AIR_TYPE.createItemStack(new ItemStackInitInfo.Simple(0, 0, null, null, 0, false));

    FullContainerType<?> getContainerType();

    ContainerSlotType getSlotType(int slot);

    Map<Byte, ContainerViewer> getViewers();

    ItemStack getItemStack(int slot);

    @UnmodifiableView
    List<ItemStack> getItemStacks();

    List<ItemData> toNetworkItemData();

    void setItemStack(int slot, ItemStack itemStack);

    void addViewer(ContainerViewer viewer);

    void removeViewer(ContainerViewer viewer);

    @Nullable
    ContainerViewer removeViewer(byte viewerId);

    void onSlotChange(int slot, ItemStack current);

    default void sendContents(ContainerViewer viewer) {
        viewer.sendContents(this);
    }

    default void sendContent(ContainerViewer viewer, int slot) {
        viewer.sendContent(this, slot);
    }
}
