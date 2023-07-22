package cn.allay.api.item.type;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemStackInitInfo extends ComponentInitInfo {

    int count();

    int damage();

    @Nullable
    NbtMap nbt();

    @Nullable
    BlockState blockState();

    record Simple(int count, int damage, @Nullable NbtMap nbt, @Nullable BlockState blockState) implements ItemStackInitInfo {
        public Simple(int count, int damage, NbtMap nbt, BlockState blockState) {
            this.count = count;
            this.damage = damage;
            this.nbt = nbt;
            this.blockState = blockState;
        }

        public Simple(int count, int damage, @Nullable NbtMap nbt) {
            this(count, damage, nbt, null);
        }

        public Simple(int count, int damage) {
            this(count, damage, null, null);
        }

        public Simple(int count) {
            this(count, 0, null, null);
        }
    }

}
