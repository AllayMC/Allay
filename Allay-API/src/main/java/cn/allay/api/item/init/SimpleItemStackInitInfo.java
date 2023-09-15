package cn.allay.api.item.init;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
public class SimpleItemStackInitInfo<T extends ItemStack> implements ItemStackInitInfo<T> {
    protected final int count;
    protected final int damage;
    protected final NbtMap nbt;
    protected final @Nullable BlockState blockState;
    protected final @Nullable Integer stackNetworkId;
    protected final boolean autoAssignStackNetworkId;
    @Getter
    @Setter
    protected ItemType<T> itemType;

    protected SimpleItemStackInitInfo(int count, int damage, NbtMap nbt, @Nullable BlockState blockState, @Nullable Integer stackNetworkId, boolean autoAssignStackNetworkId) {
        this.count = count;
        this.damage = damage;
        this.nbt = nbt;
        this.blockState = blockState;
        this.stackNetworkId = stackNetworkId;
        this.autoAssignStackNetworkId = autoAssignStackNetworkId;
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
    public NbtMap nbt() {
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected int count;
        protected int damage;
        protected NbtMapBuilder nbtBuilder = NbtMap.builder();
        protected @Nullable BlockState blockState;
        protected @Nullable Integer stackNetworkId;
        protected boolean autoAssignStackNetworkId;

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder nbt(NbtMap nbt) {
            this.nbtBuilder.putAll(nbt);
            return this;
        }

        public Builder blockState(@Nullable BlockState blockState) {
            this.blockState = blockState;
            return this;
        }

        public Builder stackNetworkId(@Nullable Integer stackNetworkId) {
            this.stackNetworkId = stackNetworkId;
            return this;
        }

        public Builder autoAssignStackNetworkId(boolean autoAssignStackNetworkId) {
            this.autoAssignStackNetworkId = autoAssignStackNetworkId;
            return this;
        }

        public <R extends ItemStack> SimpleItemStackInitInfo<R> build() {
            return new SimpleItemStackInitInfo<>(count, damage, nbtBuilder.build(), blockState, stackNetworkId, autoAssignStackNetworkId);
        }
    }
}
