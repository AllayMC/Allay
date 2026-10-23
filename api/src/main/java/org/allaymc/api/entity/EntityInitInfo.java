package org.allaymc.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

/**
 * {@code EntityInitInfo} carries a dimension, initialization NBT, and the
 * entity type selected by the factory. {@code Builder} accumulates NBT entries
 * with later writes replacing earlier keys; {@code build} retains the selected
 * dimension and materializes the accumulated compound for component
 * initialization.
 *
 * @author Cool_Loong
 */
public class EntityInitInfo implements ComponentInitInfo {
    /**
     * Dimension reference used for the initial entity location.
     */
    protected final Dimension dimension;
    /**
     * Initialization compound supplied to the entity components.
     */
    protected final NbtMap nbt;
    /**
     * {@code entityType} identifies the factory creating the entity.
     * {@code AllayEntityType.createEntity} assigns this reference before
     * constructing the component-backed entity.
     */
    @Getter
    @Setter
    protected EntityType<?> entityType;

    /**
     * Creates initialization data retaining the supplied dimension and NBT
     * references. This constructor does not select an entity type; the
     * creating entity factory supplies that reference before component
     * construction.
     */
    protected EntityInitInfo(Dimension dimension, NbtMap nbt) {
        this.dimension = dimension;
        this.nbt = nbt;
    }

    /**
     * Returns a new builder with an initially empty NBT compound.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns the dimension reference supplied for initial entity placement.
     */
    public Dimension dimension() {
        return dimension;
    }

    /**
     * Returns the initialization NBT compound retained by this information
     * object.
     */
    public NbtMap nbt() {
        return nbt;
    }

    /**
     * {@code Builder} accumulates dimension and NBT values for entity
     * initialization.
     */
    public static class Builder {
        /**
         * {@code nbtBuilder} accumulates initialization entries. All
         * configuration methods share this builder, and later writes replace
         * an earlier entry with the same key.
         */
        protected final NbtMapBuilder nbtBuilder = NbtMap.builder();
        /**
         * Dimension selected for the built initialization data.
         */
        protected Dimension dimension;

        /**
         * Selects the initial dimension and returns this configuration
         * builder.
         */
        public Builder dimension(Dimension dimension) {
            this.dimension = dimension;
            return this;
        }

        /**
         * Copies position, rotation, and dimension from the supplied location
         * into this builder. Position and rotation are stored as NBT floats,
         * so the built data uses {@code float} precision even for higher precision
         * inputs.
         */
        public Builder loc(Location3ic loc) {
            pos(loc.x(), loc.y(), loc.z());
            rot((float) loc.yaw(), (float) loc.pitch());
            this.dimension = loc.dimension();
            return this;
        }

        /**
         * Copies position, rotation, and dimension from the supplied location
         * into this builder. Position and rotation are stored as NBT floats,
         * so the built data uses {@code float} precision even for higher precision
         * inputs.
         */
        public Builder loc(Location3dc loc) {
            pos(loc.x(), loc.y(), loc.z());
            rot((float) loc.yaw(), (float) loc.pitch());
            this.dimension = loc.dimension();
            return this;
        }

        /**
         * Stores the initial position under {@code "Pos"} and returns this
         * builder. Coordinates are converted to NBT floats; later {@code pos},
         * {@code loc}, or {@code nbt} calls can replace that entry.
         */
        public Builder pos(Vector3ic pos) {
            return pos(pos.x(), pos.y(), pos.z());
        }

        /**
         * Stores the initial position under {@code "Pos"} and returns this
         * builder. Coordinates are converted to NBT floats; later {@code pos},
         * {@code loc}, or {@code nbt} calls can replace that entry.
         */
        public Builder pos(Vector3dc pos) {
            return pos(pos.x(), pos.y(), pos.z());
        }

        /**
         * Stores the initial position under {@code "Pos"} and returns this
         * builder. Coordinates are converted to NBT floats; later {@code pos},
         * {@code loc}, or {@code nbt} calls can replace that entry.
         */
        public Builder pos(double x, double y, double z) {
            // Store float components because the entity base loader reads the
            // Pos tag as a float list, including when input coordinates have higher precision.
            nbtBuilder.putList("Pos", NbtType.FLOAT, (float) x, (float) y, (float) z);
            return this;
        }

        /**
         * Stores yaw and pitch in degrees under {@code "Rotation"}. Both
         * angles are converted to NBT floats, and later writes to that key
         * replace this rotation without changing the selected dimension.
         */
        public Builder rot(double yaw, double pitch) {
            // Keep the yaw-then-pitch float layout consumed by the paired entity
            // rotation loader, so higher precision arguments use the same NBT representation.
            nbtBuilder.putList("Rotation", NbtType.FLOAT, (float) yaw, (float) pitch);
            return this;
        }

        /**
         * Stores the initial motion vector under {@code "Motion"} and returns
         * this builder. Components are converted to NBT floats, and subsequent
         * NBT entries with that key replace the complete vector.
         */
        public Builder motion(double dx, double dy, double dz) {
            // Match the physics loader's float-list representation of Motion so
            // initial velocity and later saved velocity use the same component encoding.
            nbtBuilder.putList("Motion", NbtType.FLOAT, (float) dx, (float) dy, (float) dz);
            return this;
        }

        /**
         * Stores the initial motion vector under {@code "Motion"} and returns
         * this builder. Components are converted to NBT floats, and subsequent
         * NBT entries with that key replace the complete vector.
         */
        public Builder motion(Vector3dc m) {
            return motion(m.x(), m.y(), m.z());
        }

        /**
         * Merges every supplied NBT entry into this builder's existing
         * compound. Supplied keys replace earlier values, including position
         * or rotation keys, while the separately configured dimension remains
         * unchanged.
         */
        public Builder nbt(NbtMap nbt) {
            this.nbtBuilder.putAll(nbt);
            return this;
        }

        /**
         * Creates initialization information from the accumulated compound and
         * selected dimension. This operation does not choose an entity type or
         * validate that the dimension and component-specific NBT entries are
         * suitable for construction.
         */
        public EntityInitInfo build() {
            return new EntityInitInfo(dimension, nbtBuilder.build());
        }
    }
}
