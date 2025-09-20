package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockStateDataComponent;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.utils.function.TriFunction;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.registry.InternalRegistries;
import org.jctools.maps.NonBlockingHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class BlockStateDataComponentImpl implements BlockStateDataComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_state_data_component");

    protected static final BlockStateDataComponentImpl DEFAULT = ofGlobalStatic(BlockStateData.DEFAULT);

    protected Function<BlockState, BlockStateData> dataAccessor;

    protected BlockStateDataComponentImpl(Function<BlockState, BlockStateData> dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    /**
     * Retrieves the default block state data component.
     *
     * @return the default instance returning {@link BlockStateData#DEFAULT}
     */
    public static BlockStateDataComponentImpl ofDefault() {
        return DEFAULT;
    }

    /**
     * Creates a static block state data component that always returns the same data.
     *
     * @param data the static {@link BlockStateData} to return
     * @return a new static data component instance
     */
    public static BlockStateDataComponentImpl ofGlobalStatic(BlockStateData data) {
        return new BlockStateDataComponentImpl(state -> data);
    }

    /**
     * Creates a data component using a direct dynamic accessor.
     *
     * @param accessor a function to dynamically compute {@link BlockStateData}
     * @return a new dynamic data component instance
     */
    public static BlockStateDataComponentImpl ofDirectDynamic(Function<BlockState, BlockStateData> accessor) {
        return new BlockStateDataComponentImpl(accessor);
    }

    /**
     * Creates a data component with caching support using a dynamic accessor.
     *
     * @param accessor the underlying data computation function
     * @return a cached data component instance
     */
    public static BlockStateDataComponentImpl ofCachedDynamic(Function<BlockState, BlockStateData> accessor) {
        return new BlockStateDataComponentImpl(new CachedDataAccessor(accessor));
    }

    /**
     * Creates a data component using a provided block state hash map supplier.
     *
     * @param supplier supplies the data map
     * @return a mapped data component instance
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Supplier<Map<Integer, BlockStateData>> supplier) {
        return ofMappedBlockStateHash(supplier.get());
    }

    /**
     * Creates a data component using a provided block state hash map.
     *
     * @param dataMap a map from block state hash to data
     * @return a mapped data component instance
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Map<Integer, BlockStateData> dataMap) {
        return ofMappedBlockStateHash(dataMap, BlockStateData.DEFAULT);
    }

    /**
     * Creates a data component using a provided block state hash map and a default value.
     *
     * @param dataMap     a map from block state hash to data
     * @param defaultData the default data if no match is found
     * @return a mapped data component instance
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Map<Integer, BlockStateData> dataMap, BlockStateData defaultData) {
        return new BlockStateDataComponentImpl(state -> dataMap.getOrDefault(state.blockStateHash(), defaultData));
    }

    /**
     * Creates a lazily loaded block state data component from a block type loader.
     *
     * @param loader the function loading maps per block type
     * @return a lazy-loaded data component instance
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHashLazyLoad(Function<BlockType<?>, Map<Integer, BlockStateData>> loader) {
        return ofDirectDynamic(new LazyLoaderDataAccessor(loader));
    }

    /**
     * Redefines the collision shape using the given function.
     *
     * @param shapeFunction a function to compute the new collision shape
     * @return a new data component instance with overridden collision shape
     */
    public static BlockStateDataComponentImpl ofRedefinedCollisionShape(Function<BlockState, VoxelShape> shapeFunction) {
        return ofRedefinedData((builder, blockType, blockStateHash) ->
                builder.collisionShape(shapeFunction.apply(blockType.ofState(blockStateHash))).build()
        );
    }

    /**
     * Redefines the visual shape using the given function.
     *
     * @param shapeFunction a function to compute the new shape
     * @return a new data component instance with overridden shape
     */
    public static BlockStateDataComponentImpl ofRedefinedShape(Function<BlockState, VoxelShape> shapeFunction) {
        return ofRedefinedData((builder, blockType, blockStateHash) ->
                builder.shape(shapeFunction.apply(blockType.ofState(blockStateHash))).build()
        );
    }

    /**
     * Redefines full {@link BlockStateData} using a custom tri-function.
     *
     * @param redefiner the redefining function that customizes each state's builder
     * @return a new redefined data component instance
     */
    public static BlockStateDataComponentImpl ofRedefinedData(TriFunction<BlockStateData.BlockStateDataBuilder, BlockType<?>, Integer, BlockStateData> redefiner) {
        return ofMappedBlockStateHashLazyLoad(blockType -> {
            var vanillaId = BlockId.fromIdentifier(blockType.getIdentifier());
            Objects.requireNonNull(vanillaId);
            var map = InternalRegistries.BLOCK_STATE_DATA.get(vanillaId);
            Objects.requireNonNull(map);

            Map<Integer, BlockStateData> newMap = new HashMap<>();
            map.forEach((blockStateHash, data) ->
                    newMap.put(blockStateHash, redefiner.apply(data.toBuilder(), blockType, blockStateHash))
            );
            return newMap;
        });
    }

    @Override
    public BlockStateData getBlockStateData(BlockState blockState) {
        return dataAccessor.apply(blockState);
    }

    private static class CachedDataAccessor implements Function<BlockState, BlockStateData> {
        private final Map<Integer, BlockStateData> cache = new NonBlockingHashMap<>();
        private final Function<BlockState, BlockStateData> directDataAccessor;

        public CachedDataAccessor(Function<BlockState, BlockStateData> directDataAccessor) {
            this.directDataAccessor = directDataAccessor;
        }

        @Override
        public BlockStateData apply(BlockState blockState) {
            return cache.computeIfAbsent(blockState.blockStateHash(), $ -> directDataAccessor.apply(blockState));
        }
    }

    private static class LazyLoaderDataAccessor implements Function<BlockState, BlockStateData> {
        private final Function<BlockType<?>, Map<Integer, BlockStateData>> loader;
        private Map<Integer, BlockStateData> loadedMap;

        public LazyLoaderDataAccessor(Function<BlockType<?>, Map<Integer, BlockStateData>> loader) {
            this.loader = loader;
        }

        @Override
        public BlockStateData apply(BlockState blockState) {
            if (loadedMap == null) {
                loadedMap = loader.apply(blockState.getBlockType());
            }
            return loadedMap.get(blockState.blockStateHash());
        }
    }
}
