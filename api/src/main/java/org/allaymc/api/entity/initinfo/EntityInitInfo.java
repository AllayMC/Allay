package org.allaymc.api.entity.initinfo;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3fc;

/**
 * Represents the initialization information for an entity.
 *
 * @author Cool_Loong
 */
public class EntityInitInfo implements ComponentInitInfo {
    protected final Dimension dimension;
    protected final NbtMap nbt;
    @Getter
    @Setter
    protected EntityType<?> entityType;

    protected EntityInitInfo(Dimension dimension, NbtMap nbt) {
        this.dimension = dimension;
        this.nbt = nbt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Get the dimension of the entity.
     *
     * @return the dimension
     */
    public Dimension dimension() {
        return dimension;
    }

    /**
     * Get the NBT data of the entity.
     *
     * @return the NBT data
     */
    public NbtMap nbt() {
        return nbt;
    }

    public static class Builder {
        protected final NbtMapBuilder nbtBuilder = NbtMap.builder();
        protected Dimension dimension;

        public Builder dimension(Dimension dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder loc(Location3fc loc) {
            pos(loc.x(), loc.y(), loc.z());
            rot((float) loc.yaw(), (float) loc.pitch());
            this.dimension = loc.dimension();
            return this;
        }

        public Builder pos(Vector3fc pos) {
            return pos(pos.x(), pos.y(), pos.z());
        }

        public Builder pos(float x, float y, float z) {
            nbtBuilder.putList("Pos", NbtType.FLOAT, x, y, z);
            return this;
        }

        public Builder rot(float yaw, float pitch) {
            nbtBuilder.putList("Rotation", NbtType.FLOAT, yaw, pitch);
            return this;
        }

        public Builder motion(float dx, float dy, float dz) {
            nbtBuilder.putList("Motion", NbtType.FLOAT, dx, dy, dz);
            return this;
        }

        public Builder motion(Vector3fc m) {
            return motion(m.x(), m.y(), m.z());
        }

        public Builder nbt(NbtMap nbt) {
            this.nbtBuilder.putAll(nbt);
            return this;
        }

        public EntityInitInfo build() {
            return new EntityInitInfo(dimension, nbtBuilder.build());
        }
    }
}
