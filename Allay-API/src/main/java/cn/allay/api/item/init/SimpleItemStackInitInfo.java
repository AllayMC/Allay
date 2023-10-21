package cn.allay.api.item.init;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.enchantment.EnchantmentInstance;
import cn.allay.api.item.type.ItemType;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.List;

import static cn.allay.api.item.component.base.ItemBaseComponent.EMPTY_STACK_NETWORK_ID;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
public class SimpleItemStackInitInfo<T extends ItemStack> implements ItemStackInitInfo<T> {
    protected final int count;
    protected final int meta;
    protected NbtMap extraTag;
    protected final @Nullable BlockState blockState;
    protected final int stackNetworkId;
    protected final boolean autoAssignStackNetworkId;
    @Getter
    @Setter
    protected ItemType<T> itemType;

    protected SimpleItemStackInitInfo(int count, int meta, NbtMap extraTag, @Nullable BlockState blockState, int stackNetworkId, boolean autoAssignStackNetworkId) {
        this.count = count;
        this.meta = meta;
        this.extraTag = extraTag;
        this.blockState = blockState;
        this.stackNetworkId = stackNetworkId;
        this.autoAssignStackNetworkId = autoAssignStackNetworkId;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int meta() {
        return meta;
    }

    @Override
    public NbtMap extraTag() {
        return extraTag;
    }

    @Override
    public @Nullable BlockState blockState() {
        return blockState;
    }

    @Override
    public int stackNetworkId() {
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
        protected int meta;
        protected NbtMapBuilder extraTagBuilder = NbtMap.builder();
        protected @Nullable BlockState blockState;
        protected int stackNetworkId = EMPTY_STACK_NETWORK_ID;
        protected boolean autoAssignStackNetworkId = true;

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder meta(int meta) {
            this.meta = meta;
            return this;
        }

        public Builder blockState(@Nullable BlockState blockState) {
            this.blockState = blockState;
            return this;
        }

        public Builder stackNetworkId(@Range(from = 0, to = Integer.MAX_VALUE) int stackNetworkId) {
            this.stackNetworkId = stackNetworkId;
            this.autoAssignStackNetworkId = false;
            return this;
        }

        public Builder autoAssignStackNetworkId(boolean autoAssignStackNetworkId) {
            if (autoAssignStackNetworkId) this.stackNetworkId = 0;
            this.autoAssignStackNetworkId = autoAssignStackNetworkId;
            return this;
        }

        public Builder extraTag(NbtMap extraTag) {
            extraTagBuilder.putAll(extraTag);
            return this;
        }

        public Builder durability(int durability) {
            extraTagBuilder.putInt("Damage", durability);
            return this;
        }

        public Builder customName(String customName) {
            extraTagBuilder.putString("Name", customName);
            return this;
        }

        public Builder lore(List<String> lore) {
            extraTagBuilder.putList("Lore", NbtType.STRING, lore);
            return this;
        }

        public Builder enchantments(List<EnchantmentInstance> enchantments) {
            extraTagBuilder.putList(
                    "ench", NbtType.COMPOUND,
                    enchantments.stream().map(EnchantmentInstance::saveNBT).toList()
            );
            return this;
        }

        public <R extends ItemStack> SimpleItemStackInitInfo<R> build() {
            return new SimpleItemStackInitInfo<>(
                    count, meta, extraTagBuilder.build(), blockState,
                    stackNetworkId, autoAssignStackNetworkId);
        }
    }
}
