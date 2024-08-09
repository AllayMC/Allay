package org.allaymc.api.blockentity.init;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleBlockEntityInitInfo implements BlockEntityInitInfo {
    private final Dimension dimension;
    private final NbtMap nbt;
    @Getter
    @Setter
    private BlockEntityType<?> blockEntityType;

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Dimension dimension() {
        return dimension;
    }

    @Override
    public NbtMap nbt() {
        return nbt;
    }

    public static class Builder {
        private final NbtMapBuilder nbtBuilder = NbtMap.builder();
        private Dimension dimension;

        public Builder dimension(Dimension dimension) {
            this.dimension = dimension;
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

        public SimpleBlockEntityInitInfo build() {
            return new SimpleBlockEntityInitInfo(dimension, nbtBuilder.build());
        }
    }
}
