package cn.allay.api.entity.init;

import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.math.location.Location3fc;
import cn.allay.api.world.World;
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
public class SimpleEntityInitInfo<T extends Entity> implements EntityInitInfo<T> {
    protected final World world;
    protected final NbtMap nbt;
    @Getter
    @Setter
    protected EntityType<T> entityType;

    protected SimpleEntityInitInfo(World world, NbtMap nbt) {
        this.world = world;
        this.nbt = nbt;
    }

    @Override
    public World world() {
        return world;
    }

    @Nullable
    @Override
    public NbtMap nbt() {
        return nbt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected World world;
        protected final NbtMapBuilder nbtBuilder = NbtMap.builder();

        public Builder world(World world) {
            this.world = world;
            return this;
        }

        public Builder loc(Location3fc loc) {
            pos(loc.x(), loc.y(), loc.z());
            rot((float) loc.yaw(), (float) loc.pitch());
            this.world = loc.world();
            return this;
        }

        public Builder pos(float x, float y, float z) {
            nbtBuilder.putCompound("Pos",
                    NbtMap.builder()
                            .putFloat("x", x)
                            .putFloat("y", y)
                            .putFloat("z", z)
                            .build()
            );
            return this;
        }

        public Builder rot(float yaw, float pitch) {
            nbtBuilder.putCompound("Rotation",
                    NbtMap.builder()
                            .putFloat("yaw", yaw)
                            .putFloat("pitch", pitch)
                            .build()
            );
            return this;
        }

        public Builder motion(float dx, float dy, float dz) {
            nbtBuilder.putCompound("Motion",
                    NbtMap.builder()
                            .putFloat("dx", dx)
                            .putFloat("dy", dy)
                            .putFloat("dz", dz)
                            .build()
            );
            return this;
        }

        public Builder nbt(NbtMap nbt) {
            this.nbtBuilder.putAll(nbt);
            return this;
        }

        public <R extends Entity> SimpleEntityInitInfo<R> build() {
            return new SimpleEntityInitInfo<>(world, nbtBuilder.build());
        }
    }
}
