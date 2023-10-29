package cn.allay.api.container;

import cn.allay.api.item.ItemStack;
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

    ItemStack EMPTY_SLOT_PLACE_HOLDER = ItemAirStack.AIR_TYPE.createItemStack(null);

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

    default boolean isEmpty(int slot) {
        return getItemStack(slot) == EMPTY_SLOT_PLACE_HOLDER;
    }

    @UnmodifiableView
    List<ItemStack> getItemStacks();

    @UnmodifiableView
    ItemStack[] getItemStackArray();

    List<ItemData> toNetworkItemData();

    void setItemStack(int slot, ItemStack itemStack);

    default void clearSlot(int slot) {
        setItemStack(slot, EMPTY_SLOT_PLACE_HOLDER);
    }

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

    default int tryAddItem(ItemStack itemStack, int minSlotIndex, int maxSlotIndex) {
        if (minSlotIndex > maxSlotIndex) {
            throw new IllegalArgumentException("minSlotIndex > maxSlotIndex");
        }
        if (minSlotIndex < 0) {
            throw new IllegalArgumentException("minSlotIndex is less than 0");
        }
        ItemStack[] itemStacks = getItemStackArray();
        if (minSlotIndex > itemStacks.length - 1 || maxSlotIndex > itemStacks.length - 1) {
            throw new IllegalArgumentException("minSlotIndex or maxSlotIndex is out of range");
        }
        var slot = -1;
        for (int index = minSlotIndex; index <= maxSlotIndex; index++) {
            var content = itemStacks[index];
            if (content == Container.EMPTY_SLOT_PLACE_HOLDER) {
                setItemStack(index, itemStack.copy());
                itemStack.setCount(0);
                return index;
            } else if (content.canMerge(itemStack)) {
                if (content.getCount() + itemStack.getCount() <= content.getItemAttributes().maxStackSize()) {
                    content.setCount(content.getCount() + itemStack.getCount());
                    itemStack.setCount(0);
                    onSlotChange(index);
                    return index;
                } else {
                    slot = index;
                    int count = itemStack.getCount();
                    int completion = content.getItemAttributes().maxStackSize() - content.getCount();
                    itemStack.setCount(count - completion);
                    content.setCount(content.getItemAttributes().maxStackSize());
                    onSlotChange(index);
                }
            }
        }
        return slot;
    }

    default int tryAddItem(ItemStack itemStack) {
        var array = getItemStackArray();
        return tryAddItem(itemStack, 0, array.length - 1);
    }
}
