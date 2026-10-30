package org.allaymc.api.block.type;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * {@code BlockStateGetter} builds named block-state NBT requests and resolves them
 * through the installed {@link NBTIO} service. Callers can specify named properties
 * or a legacy numeric value, allowing the installed updater to interpret the
 * request without direct references to generated block-type fields.
 *
 * @author daoge_cmd | harry-xi
 */
@Slf4j
@UtilityClass
public final class BlockStateGetter {
    /**
     * Creates a mutable request builder for the supplied block name. Blank names
     * throw {@code IllegalArgumentException}, {@code null} fails immediately, and
     * identifier parsing is deferred until the request reaches the installed NBT
     * service.
     */
    public static Getter name(String name) {
        return new Getter(name);
    }

    /**
     * {@code Getter} accumulates one block-state request using named properties or
     * a legacy value.
     */
    public static final class Getter {
        /**
         * Mutable root block-state request.
         */
        private final NbtMapBuilder nbtBuilder = NbtMap.builder();
        /**
         * {@code stateBuilder} remains {@code null} until the first named property
         * is supplied. Once present, it prevents selection of the legacy
         * numeric-value form.
         */
        private NbtMapBuilder stateBuilder;

        /**
         * Creates a request with the supplied nonblank block name. The name is
         * retained without normalization, and this constructor does not resolve its
         * identifier or check whether a block type is registered.
         */
        private Getter(String name) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            nbtBuilder.putString("name", name);
        }

        /**
         * Adds or replaces a named serialized property and returns this builder.
         * Once {@code val} has been selected, this method throws {@code
         * IllegalStateException} to prevent combining named properties with the
         * legacy request form.
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
         * Sets or replaces the legacy numeric value and returns this builder. Once
         * any named property has been added, this method throws {@code
         * IllegalStateException} because the request must use only one
         * representation.
         */
        public Getter val(int val) {
            if (stateBuilder != null) {
                throw new IllegalStateException("Cannot set val when properties are set");
            }
            nbtBuilder.putInt("val", val);
            return this;
        }

        /**
         * Resolves the accumulated request through the installed NBT service. That
         * service may return the unknown block's default state for unsupported
         * names or versions, while malformed identifiers and property conversions
         * can still throw instead of producing a fallback.
         */
        public BlockState blockState() {
            if (stateBuilder != null) {
                nbtBuilder.putCompound("states", stateBuilder.build());
            }

            return NBTIO.getAPI().fromBlockStateNBT(nbtBuilder.build());
        }
    }
}