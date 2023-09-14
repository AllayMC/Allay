package cn.allay.api.item.init;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
final class SimpleItemStackInitInfo<T extends ItemStack> implements ItemStackInitInfo<T> {
    private final int count;
    private final int damage;
    private final @Nullable NbtMap nbt;
    private final @Nullable BlockState blockState;
    private final @Nullable Integer stackNetworkId;
    private final boolean autoAssignStackNetworkId;
    @Getter
    @Setter
    private ItemType<T> itemType;

    public SimpleItemStackInitInfo(int count, int damage, @Nullable NbtMap nbt, @Nullable BlockState blockState, @Nullable Integer stackNetworkId, boolean autoAssignStackNetworkId) {
        this.count = count;
        this.damage = damage;
        this.nbt = nbt;
        this.blockState = blockState;
        this.stackNetworkId = stackNetworkId;
        this.autoAssignStackNetworkId = autoAssignStackNetworkId;
    }

    public SimpleItemStackInitInfo(int count, int damage, NbtMap nbt, BlockState blockState, Integer stackNetworkId) {
        this(count, damage, nbt, blockState, stackNetworkId, true);
    }

    public SimpleItemStackInitInfo(int count, int damage, NbtMap nbt, BlockState blockState) {
        this(count, damage, nbt, blockState, null);
    }

    public SimpleItemStackInitInfo(int count, int damage, @Nullable NbtMap nbt) {
        this(count, damage, nbt, null);
    }

    public SimpleItemStackInitInfo(int count, int damage) {
        this(count, damage, null, null);
    }

    public SimpleItemStackInitInfo(int count) {
        this(count, 0, null, null);
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public @Nullable NbtMap nbt() {
        return nbt;
    }

    @Override
    public @Nullable BlockState blockState() {
        return blockState;
    }

    @Override
    public @Nullable Integer stackNetworkId() {
        return stackNetworkId;
    }

    @Override
    public boolean autoAssignStackNetworkId() {
        return autoAssignStackNetworkId;
    }
}
