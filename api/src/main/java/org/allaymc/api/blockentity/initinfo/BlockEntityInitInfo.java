package org.allaymc.api.blockentity.initinfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * Represents the initialization information for a block entity.
 *
 * @author daoge_cmd
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEntityInitInfo implements ComponentInitInfo {
    private final Dimension dimension;
    private final NbtMap nbt;
    @Getter
    @Setter
    private BlockEntityType<?> blockEntityType;

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Get the dimension of the block entity.
     *
     * @return the dimension
     */
    public Dimension dimension() {
        return dimension;
    }

    /**
     * Get the NBT data of the block entity.
     *
     * @return the NBT data
     */
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

        public BlockEntityInitInfo build() {
            return new BlockEntityInitInfo(dimension, nbtBuilder.build());
        }
    }
}
