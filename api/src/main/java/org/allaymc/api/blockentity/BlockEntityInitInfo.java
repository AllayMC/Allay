package org.allaymc.api.blockentity;

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
 * {@code BlockEntityInitInfo} carries a dimension and initial NBT into
 * block-entity component construction. The builder accumulates NBT entries with
 * later writes replacing equal keys; it does not validate dimension
 * availability or required tags. The creating block-entity type assigns its own
 * type reference before invoking providers.
 *
 * @author daoge_cmd
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEntityInitInfo implements ComponentInitInfo {
    /**
     * Target dimension reference.
     */
    private final Dimension dimension;
    /**
     * Initial component NBT payload.
     */
    private final NbtMap nbt;
    /**
     * {@code blockEntityType} identifies the creating type.
     * {@code createBlockEntity} assigns this reference before component
     * construction, and reuse with another type replaces the reference through
     * the generated setter.
     */
    @Getter
    @Setter
    private BlockEntityType<?> blockEntityType;

    /**
     * Creates a builder with empty NBT and no selected dimension. Callers
     * supply the dimension before constructing entities that need world access;
     * the builder does not infer it from coordinates or stored NBT.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns the dimension reference supplied for the new block entity. The
     * builder permits an unset dimension and returns {@code null} in that case;
     * consumers requiring world access must provide one before construction.
     */
    public Dimension dimension() {
        return dimension;
    }

    /**
     * Returns the initial NBT map retained for component initialization.
     */
    public NbtMap nbt() {
        return nbt;
    }

    /**
     * {@code Builder} accumulates one dimension reference and initialization
     * NBT entries whose later values replace entries with equal keys.
     */
    public static class Builder {
        /**
         * Mutable initial NBT entries.
         */
        private final NbtMapBuilder nbtBuilder = NbtMap.builder();
        /**
         * Selected dimension reference.
         */
        private Dimension dimension;

        /**
         * Selects the dimension reference and returns this same initialization
         * builder. {@code null} is retained without validation, so callers must
         * provide a usable dimension when subsequent component behavior
         * requires world access.
         */
        public Builder dimension(Dimension dimension) {
            this.dimension = dimension;
            return this;
        }

        /**
         * Stores block coordinates in the pending NBT and returns this builder.
         * These values replace existing {@code "x"}, {@code "y"}, and
         * {@code "z"} tags; a later {@code nbt} call can replace them again
         * with supplied entries.
         */
        public Builder pos(int x, int y, int z) {
            nbtBuilder.putInt("x", x);
            nbtBuilder.putInt("y", y);
            nbtBuilder.putInt("z", z);
            return this;
        }

        /**
         * Stores the pending {@code "CustomName"} tag and returns this same
         * builder. Later NBT merges may replace the tag; callers leave a fresh
         * block entity unnamed by omitting this operation rather than supplying
         * {@code null}.
         */
        public Builder customName(String customName) {
            nbtBuilder.putString("CustomName", customName);
            return this;
        }

        /**
         * Merges all supplied NBT entries and returns this same builder.
         * Existing equal keys are replaced while unrelated entries remain,
         * allowing this call to override earlier position or name selections.
         */
        public Builder nbt(NbtMap nbt) {
            nbtBuilder.putAll(nbt);
            return this;
        }

        /**
         * Builds initialization information from the current dimension and NBT.
         * The builder performs no required-field validation, and
         * {@code blockEntityType} remains {@code null} until the chosen type
         * assigns itself during {@code createBlockEntity}.
         */
        public BlockEntityInitInfo build() {
            return new BlockEntityInitInfo(dimension, nbtBuilder.build());
        }
    }
}
