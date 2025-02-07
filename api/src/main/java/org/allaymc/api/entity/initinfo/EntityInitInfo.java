package org.allaymc.api.entity.initinfo;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3dc;

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

        public Builder loc(Location3dc loc) {
            pos(loc.x(), loc.y(), loc.z());
            rot((float) loc.yaw(), (float) loc.pitch());
            this.dimension = loc.dimension();
            return this;
        }

        public Builder pos(Vector3dc pos) {
            return pos(pos.x(), pos.y(), pos.z());
        }

        public Builder pos(double x, double y, double z) {
            nbtBuilder.putList("Pos", NbtType.FLOAT, (float) x, (float) y, (float) z);
            return this;
        }

        public Builder rot(double yaw, double pitch) {
            nbtBuilder.putList("Rotation", NbtType.FLOAT, (float) yaw, (float) pitch);
            return this;
        }

        public Builder motion(double dx, double dy, double dz) {
            nbtBuilder.putList("Motion", NbtType.FLOAT, (float) dx, (float) dy, (float) dz);
            return this;
        }

        public Builder motion(Vector3dc m) {
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
