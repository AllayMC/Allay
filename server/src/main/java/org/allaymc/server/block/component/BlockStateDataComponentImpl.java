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
 * {@code BlockStateDataComponentImpl} resolves block-state metadata through a
 * configurable accessor. Its factories select shared static data, direct
 * calculation, hash-based caching, or a lazily loaded map; these choices have
 * different missing-value and sharing rules, and the lazy accessor retains the map
 * chosen by its first load.
 *
 * @author daoge_cmd
 */
public class BlockStateDataComponentImpl implements BlockStateDataComponent {
    /**
     * {@code IDENTIFIER} names this component in block behavior assembly, allowing
     * the builder to supply default metadata when no provider is configured.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_state_data_component");

    /**
     * {@code DEFAULT} is the shared component returning {@code
     * BlockStateData.DEFAULT} for every state; users must not reconfigure its
     * accessor.
     */
    protected static final BlockStateDataComponentImpl DEFAULT = ofGlobalStatic(BlockStateData.DEFAULT);

    /**
     * {@code dataAccessor} defines every metadata lookup. Subclasses must finish
     * configuring it before sharing the component because lookup invokes it without
     * replacement synchronization.
     */
    protected Function<BlockState, BlockStateData> dataAccessor;

    /**
     * Creates a component retaining the supplied metadata accessor without
     * validation. Every lookup invokes this reference directly, so the caller must
     * provide an accessor with the intended state and threading rules.
     */
    protected BlockStateDataComponentImpl(Function<BlockState, BlockStateData> dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    /**
     * Returns the shared component that supplies {@code BlockStateData.DEFAULT} for
     * every state. Callers must preserve its configuration because block types
     * without specialized metadata may use the same component instance.
     */
    public static BlockStateDataComponentImpl ofDefault() {
        return DEFAULT;
    }

    /**
     * Creates a component returning the same supplied data reference for every
     * state. The reference is not copied or validated, so supplying {@code null}
     * makes every lookup return {@code null}.
     */
    public static BlockStateDataComponentImpl ofGlobalStatic(BlockStateData data) {
        return new BlockStateDataComponentImpl(state -> data);
    }

    /**
     * Creates a component that invokes the supplied accessor on every lookup.
     * Results are not cached, and the caller remains responsible for the accessor's
     * supported states and concurrent-use requirements.
     */
    public static BlockStateDataComponentImpl ofDirectDynamic(Function<BlockState, BlockStateData> accessor) {
        return new BlockStateDataComponentImpl(accessor);
    }

    /**
     * Creates a component that caches metadata by signed block-state hash. The
     * accessor must tolerate repeated calculations and any concurrent calls
     * reaching it; a {@code null} result does not establish a cached entry.
     */
    public static BlockStateDataComponentImpl ofCachedDynamic(Function<BlockState, BlockStateData> accessor) {
        return new BlockStateDataComponentImpl(new CachedDataAccessor(accessor));
    }

    /**
     * Creates a mapped component by invoking the supplier immediately once and
     * retaining the returned map. Lookup uses {@code BlockStateData.DEFAULT} for
     * missing hashes, rather than asking the supplier for later replacements.
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Supplier<Map<Integer, BlockStateData>> supplier) {
        return ofMappedBlockStateHash(supplier.get());
    }

    /**
     * Creates a component retaining the supplied hash-to-data map and using {@code
     * BlockStateData.DEFAULT} for absent keys. Later map changes are visible to
     * lookup, so callers must manage its mutation and sharing.
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Map<Integer, BlockStateData> dataMap) {
        return ofMappedBlockStateHash(dataMap, BlockStateData.DEFAULT);
    }

    /**
     * Creates a component retaining the supplied map and fallback reference. Absent
     * hashes use {@code defaultData}, while a key explicitly mapped to {@code null}
     * returns {@code null} through {@code getOrDefault}.
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Map<Integer, BlockStateData> dataMap, BlockStateData defaultData) {
        return new BlockStateDataComponentImpl(state -> dataMap.getOrDefault(state.blockStateHash(), defaultData));
    }

    /**
     * Creates a component that loads and retains a metadata map on first lookup.
     * The accessor does not synchronize initialization, and later states use that
     * retained map even if they belong to another block type.
     */
    public static BlockStateDataComponentImpl ofMappedBlockStateHashLazyLoad(Function<BlockType<?>, Map<Integer, BlockStateData>> loader) {
        return ofDirectDynamic(new LazyLoaderDataAccessor(loader));
    }

    /**
     * Creates lazily redefined vanilla metadata with the supplied collision-shape
     * calculation applied per stored state. Other data comes from the vanilla
     * registry, and state lookup must be available when the first metadata request
     * triggers loading.
     */
    public static BlockStateDataComponentImpl ofRedefinedCollisionShape(Function<BlockState, VoxelShape> shapeFunction) {
        return ofRedefinedData((builder, blockType, blockStateHash) ->
                builder.collisionShape(shapeFunction.apply(blockType.ofState(blockStateHash))).build()
        );
    }

    /**
     * Creates lazily redefined vanilla metadata with the supplied selection and
     * inside-detection shape calculation. Other data comes from the vanilla
     * registry, and the first metadata request must occur after state lookup
     * becomes available.
     */
    public static BlockStateDataComponentImpl ofRedefinedShape(Function<BlockState, VoxelShape> shapeFunction) {
        return ofRedefinedData((builder, blockType, blockStateHash) ->
                builder.shape(shapeFunction.apply(blockType.ofState(blockStateHash))).build()
        );
    }

    /**
     * Creates a lazy map by applying the redefiner to builders copied from vanilla
     * state metadata. The requested type must have a known vanilla identifier and
     * registered source data; missing entries fail rather than producing default
     * metadata.
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

    /**
     * Returns the metadata selected by this component's configured accessor for the
     * supplied state.
     */
    @Override
    public BlockStateData getBlockStateData(BlockState blockState) {
        return dataAccessor.apply(blockState);
    }

    /**
     * {@code CachedDataAccessor} retains computed metadata keyed by the state's
     * signed palette hash.
     */
    private static class CachedDataAccessor implements Function<BlockState, BlockStateData> {
        /**
         * {@code cache} retains successful non-{@code null} accessor results by
         * hash. Distinct state objects with the same hash therefore share the same
         * cache lookup.
         */
        private final Map<Integer, BlockStateData> cache = new NonBlockingHashMap<>();
        /**
         * Underlying metadata computation function.
         */
        private final Function<BlockState, BlockStateData> directDataAccessor;

        /**
         * Creates a hash-cached accessor retaining the supplied metadata
         * calculation function.
         */
        public CachedDataAccessor(Function<BlockState, BlockStateData> directDataAccessor) {
            this.directDataAccessor = directDataAccessor;
        }

        /**
         * Returns cached metadata or computes data for an absent state hash. A
         * {@code null} result leaves no entry, so later calls can retry the
         * accessor instead of reusing a cached {@code null} value.
         */
        @Override
        public BlockStateData apply(BlockState blockState) {
            return cache.computeIfAbsent(blockState.blockStateHash(), $ -> directDataAccessor.apply(blockState));
        }
    }

    /**
     * {@code LazyLoaderDataAccessor} retains the metadata map loaded from its first
     * requested block type.
     */
    private static class LazyLoaderDataAccessor implements Function<BlockState, BlockStateData> {
        /**
         * Deferred block-type map loader.
         */
        private final Function<BlockType<?>, Map<Integer, BlockStateData>> loader;
        /**
         * {@code loadedMap} starts as {@code null} and retains the first loaded map
         * without synchronization. Consumers must arrange initialization before
         * concurrent access when deterministic single-map publication is required.
         */
        private Map<Integer, BlockStateData> loadedMap;

        /**
         * Creates an accessor that defers the supplied block-type map loader until
         * lookup.
         */
        public LazyLoaderDataAccessor(Function<BlockType<?>, Map<Integer, BlockStateData>> loader) {
            this.loader = loader;
        }

        /**
         * Loads a map when none is retained, then looks up the state's hash in that
         * map. Missing hashes return {@code null}; initialization is
         * unsynchronized, and loading a {@code null} map causes this lookup to
         * fail.
         */
        @Override
        public BlockStateData apply(BlockState blockState) {
            if (loadedMap == null) {
                loadedMap = loader.apply(blockState.getBlockType());
            }
            return loadedMap.get(blockState.blockStateHash());
        }
    }
}
