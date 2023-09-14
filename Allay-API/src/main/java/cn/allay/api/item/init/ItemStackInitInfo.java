package cn.allay.api.item.init;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemStackInitInfo<T extends ItemStack> extends ComponentInitInfo {

    int count();

    int damage();

    @Nullable
    NbtMap nbt();

    @Nullable
    BlockState blockState();

    @Nullable
    Integer stackNetworkId();

    boolean autoAssignStackNetworkId();

    ItemType<T> getItemType();

    void setItemType(ItemType<T> itemType);

    static <T extends ItemStack> ItemStackInitInfo<T> of(int count, int damage, @Nullable NbtMap nbt, @Nullable BlockState blockState, @Nullable Integer stackNetworkId, boolean autoAssignStackNetworkId) {
        return new SimpleItemStackInitInfo<>(count, damage, nbt, blockState, stackNetworkId, autoAssignStackNetworkId);
    }

    static <T extends ItemStack> ItemStackInitInfo<T> of(int count, int damage, NbtMap nbt, BlockState blockState, Integer stackNetworkId) {
        return new SimpleItemStackInitInfo<>(count, damage, nbt, blockState, stackNetworkId);
    }

    static <T extends ItemStack> ItemStackInitInfo<T> of(int count, int damage, NbtMap nbt, BlockState blockState) {
        return new SimpleItemStackInitInfo<>(count, damage, nbt, blockState);
    }

    static <T extends ItemStack> ItemStackInitInfo<T> of(int count, int damage, @Nullable NbtMap nbt) {
        return new SimpleItemStackInitInfo<>(count, damage, nbt);
    }

    static <T extends ItemStack> ItemStackInitInfo<T> of(int count, int damage) {
        return new SimpleItemStackInitInfo<>(count, damage);
    }

    static <T extends ItemStack> ItemStackInitInfo<T> of(int count) {
        return new SimpleItemStackInitInfo<>(count);
    }
}
