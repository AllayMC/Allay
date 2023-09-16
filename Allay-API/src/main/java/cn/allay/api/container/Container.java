package cn.allay.api.container;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3ic;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface Container {

    ItemStack AIR_STACK = ItemAirStack.AIR_TYPE.createItemStack(SimpleItemStackInitInfo.builder().stackNetworkId(0).build());

    FullContainerType<?> getContainerType();

    default void onOpen(ContainerViewer viewer) {

    }

    default void onClose(ContainerViewer viewer) {

    }

    default boolean hasBlockPos() {
        return false;
    }

    default Vector3ic getBlockPos() {
        throw new UnsupportedOperationException();
    }

    default ContainerSlotType getSlotType(int slot) {
        return getContainerType().getSlotType(slot);
    }

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

    NbtList<NbtMap> save();

    void load(List<NbtMap> nbtList);

    default void sendContents(ContainerViewer viewer) {
        viewer.sendContents(this);
    }

    default void sendContent(ContainerViewer viewer, int slot) {
        viewer.sendContent(this, slot);
    }
}
