package cn.allay.api.blockentity.init;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.world.World;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class SimpleBlockEntityInitInfo<T extends BlockEntity> implements BlockEntityInitInfo<T> {
    private final World world;
    private final NbtMap nbt;
    @Getter
    @Setter
    private BlockEntityType<T> blockEntityType;

    protected SimpleBlockEntityInitInfo(World world, NbtMap nbt) {
        this.world = world;
        this.nbt = nbt;
    }

    @Override
    public World world() {
        return world;
    }

    @Override
    public NbtMap nbt() {
        return nbt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private World world;
        private final NbtMapBuilder nbtBuilder = NbtMap.builder();

        public Builder world(World world) {
            this.world = world;
            return this;
        }

        public Builder pos(int x, int y, int z) {
            nbtBuilder.putInt("x", x);
            nbtBuilder.putInt("y", y);
            nbtBuilder.putInt("z", z);
            return this;
        }

        public Builder customName(String customName) {
            nbtBuilder.putString("CustomName", customName);
            return this;
        }

        public Builder nbt(NbtMap nbt) {
            nbtBuilder.putAll(nbt);
            return this;
        }

        public <R extends BlockEntity> SimpleBlockEntityInitInfo<R> build() {
            return new SimpleBlockEntityInitInfo<>(world, nbtBuilder.build());
        }
    }
}
