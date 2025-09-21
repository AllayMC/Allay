package org.allaymc.api.block.type;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * BlockStateGetter is used to get a block state safely, which means that
 * the plugin developers can get an item type without worrying about being broken
 * in the next minecraft version compared to using {@link BlockTypes} directly.
 *
 * @author daoge_cmd | harry-xi
 */
@Slf4j
@UtilityClass
public final class BlockStateGetter {
    /**
     * Get a block type by its name.
     *
     * @param name The name of the block type
     * @return The {@link BlockStateGetter.Getter} object
     */
    public static Getter name(String name) {
        return new Getter(name);
    }

    public static final class Getter {
        private final NbtMapBuilder nbtBuilder = NbtMap.builder();
        private NbtMapBuilder stateBuilder;

        private Getter(String name) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            nbtBuilder.putString("name", name);
        }

        /**
         * Set the property of the block.
         *
         * @param name  The name of the property
         * @param value The value of the property
         * @return The {@link Getter} object
         */
        public Getter property(String name, Object value) {
            if (nbtBuilder.containsKey("val")) {
                throw new IllegalStateException("Cannot set properties when val is set");
            }
            if (stateBuilder == null) {
                stateBuilder = NbtMap.builder();
            }
            stateBuilder.put(name, value);
            return this;
        }

        /**
         * Set the val of the block.
         *
         * @param val The val of the block
         * @return The {@link Getter} object
         */
        public Getter val(int val) {
            if (stateBuilder != null) {
                throw new IllegalStateException("Cannot set val when properties are set");
            }
            nbtBuilder.putInt("val", val);
            return this;
        }

        /**
         * Try to get the block state.
         *
         * @return The block state
         */
        public BlockState blockState() {
            if (stateBuilder != null) {
                nbtBuilder.putCompound("states", stateBuilder.build());
            }

            return NBTIO.getAPI().fromBlockStateNBT(nbtBuilder.build());
        }
    }
}