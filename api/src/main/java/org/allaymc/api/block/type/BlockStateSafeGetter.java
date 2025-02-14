package org.allaymc.api.block.type;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.updater.block.BlockStateUpdater_1_21_40;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * BlockStateSafeGetter is used to get a block state safely, which means that
 * the plugin developers can get an item type without worrying about being broken
 * in the next minecraft version compared to using {@link BlockTypes} directly.
 *
 * @author daoge_cmd | harry-xi
 */
@Slf4j
@UtilityClass
public final class BlockStateSafeGetter {

    /**
     * Get the block state from the block state NBT.
     *
     * @param nbt the block state NBT.
     *
     * @return the block state, or the default state of {@code BlockTypes.UNKNOWN}
     * if the block state is not found, or the block state version is too new.
     */
    public static BlockState fromNBT(NbtMap nbt) {
        var version = nbt.getInt("version");
        if (version > ProtocolInfo.BLOCK_STATE_VERSION_NUM) {
            log.warn("Block state version is too new: {}", nbt);
            return BlockTypes.UNKNOWN.getDefaultState();
        }

        if (version < ProtocolInfo.BLOCK_STATE_VERSION_NUM) {
            nbt = BlockStateUpdaters.updateBlockState(nbt, BlockStateUpdaters.LATEST_VERSION);
        }

        // Make sure that tree map is used
        // If the map inside states nbt is not tree map
        // the block state hash will be wrong!
        var states = new TreeMap<>(nbt.getCompound("states"));
        // To calculate the hash of the block state
        // "name" field must be in the first place
        var tag = NbtMap.builder()
                .putString("name", nbt.getString("name"))
                .putCompound("states", NbtMap.fromMap(states))
                .build();
        int blockStateHash = HashUtils.fnv1a_32_nbt(tag);

        // Get block state by hash
        BlockState blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash);
        if (blockState != null) {
            return blockState;
        }

        log.warn("Block state not found: {}", nbt);
        return BlockTypes.UNKNOWN.getDefaultState();
    }

    /**
     * Get a block type by its name.
     *
     * @param name The name of the block type.
     *
     * @return The {@link BlockStateSafeGetter.Getter} object.
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
         * @param name  The name of the property.
         * @param value The value of the property.
         *
         * @return The {@link Getter} object.
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
         * @param val The val of the block.
         *
         * @return The {@link Getter} object.
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
         * @return The block state.
         */
        public BlockState blockState() {
            if (stateBuilder != null) {
                nbtBuilder.putCompound("states", stateBuilder.build());
            }

            // Get block type
            var updatedNbt = BlockStateUpdaters.updateBlockState(nbtBuilder.build(), BlockStateUpdater_1_21_40.INSTANCE.getVersion());
            var blockType = Registries.BLOCKS.get(new Identifier(updatedNbt.getString("name")));
            if (blockType == null) {
                throw new IllegalArgumentException("Unknown block type " + updatedNbt.getString("name"));
            }

            // Add missing properties
            var updatedStates = updatedNbt.getCompound("states");
            var updatedStatesBuilder = updatedStates.toBuilder();
            for (var entry : blockType.getDefaultState().getPropertyValues().entrySet()) {
                if (updatedStatesBuilder.containsKey(entry.getKey().getName())) {
                    continue;
                }
                updatedStatesBuilder.put(entry.getKey().getName(), entry.getValue().getSerializedValue());
            }
            updatedStates = updatedStatesBuilder.build();

            // Create block property value list
            List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> blockPropertyValues = new ArrayList<>();
            for (var entry : updatedStates.entrySet()) {
                blockPropertyValues.add(blockType.getProperties().get(entry.getKey()).tryCreateValue(entry.getValue()));
            }

            // Get block state
            var blockState = blockType.ofState(blockPropertyValues);
            if (blockState == null) {
                throw new IllegalArgumentException("Invalid block state " + updatedNbt);
            }
            return blockState;
        }
    }
}