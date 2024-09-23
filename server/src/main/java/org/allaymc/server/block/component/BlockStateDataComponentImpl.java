package org.allaymc.server.block.component;

import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.component.data.BlockStateDataComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import org.apache.commons.lang3.function.TriFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class BlockStateDataComponentImpl implements BlockStateDataComponent {

    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:block_state_data_component");

    protected static final BlockStateDataComponentImpl DEFAULT = BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.DEFAULT);

    protected Function<BlockState, BlockStateData> attributeAccessor;

    protected BlockStateDataComponentImpl(Function<BlockState, BlockStateData> attributeAccessor) {
        this.attributeAccessor = attributeAccessor;
    }

    public static BlockStateDataComponentImpl ofDefault() {
        return DEFAULT;
    }

    public static BlockStateDataComponentImpl ofGlobalStatic(BlockStateData attributes) {
        return new BlockStateDataComponentImpl(state -> attributes);
    }

    public static BlockStateDataComponentImpl ofDirectDynamic(Function<BlockState, BlockStateData> directAttributeAccessor) {
        return new BlockStateDataComponentImpl(directAttributeAccessor);
    }

    public static BlockStateDataComponentImpl ofCachedDynamic(Function<BlockState, BlockStateData> directAttributeAccessor) {
        return new BlockStateDataComponentImpl(new CachedAttributeAccessor(directAttributeAccessor));
    }

    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Supplier<Map<Integer, BlockStateData>> attributeMapSupplier) {
        return ofMappedBlockStateHash(attributeMapSupplier.get());
    }

    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Map<Integer, BlockStateData> attributeMap) {
        return ofMappedBlockStateHash(attributeMap, BlockStateData.DEFAULT);
    }

    public static BlockStateDataComponentImpl ofMappedBlockStateHash(Map<Integer, BlockStateData> attributeMap, BlockStateData defaultAttributes) {
        return new BlockStateDataComponentImpl(state -> attributeMap.getOrDefault(state.blockStateHash(), defaultAttributes));
    }

    public static BlockStateDataComponentImpl ofMappedBlockStateHashLazyLoad(Function<BlockType<?>, Map<Integer, BlockStateData>> lazyLoader) {
        return ofDirectDynamic(new LazyLoaderAttributeAccessor(lazyLoader));
    }

    public static BlockStateDataComponentImpl ofRedefinedAABB(Function<BlockState, VoxelShape> aabbRedefiner) {
        return ofRedefinedData((builder, blockType, blockStateHash) -> builder.collisionShape(aabbRedefiner.apply(blockType.ofState(blockStateHash))).build());
    }

    public static BlockStateDataComponentImpl ofRedefinedDamageReductionFactor(Function<BlockState, Float> reductionRedefiner) {
        return ofRedefinedData((builder, blockType, blockStateHash) -> builder.fallDamageReductionFactor(reductionRedefiner.apply(blockType.ofState(blockStateHash))).build());
    }

    public static BlockStateDataComponentImpl ofRedefinedCanResetFallDistance(Function<BlockState, Boolean> canResetFallDistanceRedefiner) {
        return ofRedefinedData((builder, blockType, blockStateHash) -> builder.canResetFallDistance(canResetFallDistanceRedefiner.apply(blockType.ofState(blockStateHash))).build());
    }

    public static BlockStateDataComponentImpl ofRedefinedData(TriFunction<BlockStateData.BlockStateDataBuilder, BlockType<?>, Integer, BlockStateData> redefiner) {
        return ofMappedBlockStateHashLazyLoad(blockType -> {
            var vanillaId = BlockId.fromIdentifier(blockType.getIdentifier());
            Objects.requireNonNull(vanillaId);
            var attributeMap = Registries.BLOCK_STATE_DATA.get(vanillaId);
            Objects.requireNonNull(attributeMap);
            var newAttributeMap = new HashMap<Integer, BlockStateData>();
            attributeMap.forEach((blockStateHash, attribute) ->
                    newAttributeMap.put(
                            blockStateHash,
                            redefiner.apply(attribute.toBuilder(), blockType, blockStateHash)
                    )
            );
            return newAttributeMap;
        });
    }

    @Override
    public BlockStateData getBlockStateData(BlockState blockState) {
        return attributeAccessor.apply(blockState);
    }

    private static class CachedAttributeAccessor implements Function<BlockState, BlockStateData> {
        private final Map<Integer, BlockStateData> attributeMap = new Int2ObjectNonBlockingMap<>();
        private final Function<BlockState, BlockStateData> directAttributeAccessor;

        public CachedAttributeAccessor(Function<BlockState, BlockStateData> directAttributeAccessor) {
            this.directAttributeAccessor = directAttributeAccessor;
        }

        @Override
        public BlockStateData apply(BlockState blockState) {
            return attributeMap.computeIfAbsent(blockState.blockStateHash(), $ -> directAttributeAccessor.apply(blockState));
        }
    }

    private static class LazyLoaderAttributeAccessor implements Function<BlockState, BlockStateData> {

        private final Function<BlockType<?>, Map<Integer, BlockStateData>> lazyLoader;
        private Map<Integer, BlockStateData> lazyLoadAttributeMap;

        public LazyLoaderAttributeAccessor(Function<BlockType<?>, Map<Integer, BlockStateData>> lazyLoader) {
            this.lazyLoader = lazyLoader;
        }

        @Override
        public BlockStateData apply(BlockState blockState) {
            if (lazyLoadAttributeMap == null) lazyLoadAttributeMap = lazyLoader.apply(blockState.getBlockType());
            return lazyLoadAttributeMap.get(blockState.blockStateHash());
        }
    }
}