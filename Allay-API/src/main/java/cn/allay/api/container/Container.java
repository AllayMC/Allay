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
import java.util.function.Consumer;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface Container {

    ItemStack AIR_STACK = ItemAirStack.AIR_TYPE.createItemStack(SimpleItemStackInitInfo.builder().stackNetworkId(0).build());

    FullContainerType<?> getContainerType();

    void onOpen(ContainerViewer viewer);

    void onClose(ContainerViewer viewer);

    void addOnOpenListener(Consumer<ContainerViewer> listener);

    void removeOnOpenListener(Consumer<ContainerViewer> listener);

    void addOnCloseListener(Consumer<ContainerViewer> listener);

    void removeOnCloseListener(Consumer<ContainerViewer> listener);

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

    @UnmodifiableView
    ItemStack[] getItemStackArray();

    List<ItemData> toNetworkItemData();

    void setItemStack(int slot, ItemStack itemStack);

    void addViewer(ContainerViewer viewer);

    void removeViewer(ContainerViewer viewer);

    @Nullable
    ContainerViewer removeViewer(byte viewerId);

    void onSlotChange(int slot);

    NbtList<NbtMap> saveNBT();

    void loadNBT(List<NbtMap> nbtList);

    default void sendContents(ContainerViewer viewer) {
        viewer.sendContents(this);
    }

    default void sendContent(ContainerViewer viewer, int slot) {
        viewer.sendContent(this, slot);
    }

    @Nullable
    default ItemStack tryAddItem(ItemStack itemStack) {
        return tryAddItem(itemStack, false);
    }

    @Nullable
    default ItemStack tryAddItem(ItemStack itemStack, boolean mustAddAll) {
        ItemStack[] itemStacks = getItemStackArray();
        for (int index = 0; index < itemStacks.length; index++) {
            var content = itemStacks[index];
            if (content == Container.AIR_STACK) {
                setItemStack(index, itemStack);
                return null;
            } else if (content.canMerge(itemStack)) {
                if (content.getCount() + itemStack.getCount() <= content.getItemAttributes().maxStackSize()) {
                    content.setCount(content.getCount() + itemStack.getCount());
                    onSlotChange(index);
                    return null;
                } else if (mustAddAll) {
                    return itemStack;
                } else {
                    int count = itemStack.getCount();
                    count -= content.getItemAttributes().maxStackSize() - content.getCount();
                    itemStack.setCount(count);
                    content.setCount(content.getItemAttributes().maxStackSize());
                    onSlotChange(index);
                    return itemStack;
                }
            }
        }
        return itemStack;
    }
}
