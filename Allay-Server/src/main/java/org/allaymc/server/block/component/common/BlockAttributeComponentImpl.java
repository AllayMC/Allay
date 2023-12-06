package org.allaymc.server.block.component.common;

import org.allaymc.api.block.component.common.BlockAttributeComponent;
import org.allaymc.api.block.component.common.BlockAttributes;
import org.allaymc.api.block.registry.VanillaBlockAttributeRegistry;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.math.voxelshape.VoxelShape;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public class BlockAttributeComponentImpl implements BlockAttributeComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_attribute_component");
    protected static final BlockAttributeComponentImpl DEFAULT = BlockAttributeComponentImpl.ofGlobalStatic(BlockAttributes.DEFAULT);
    protected Function<BlockState, BlockAttributes> attributeAccessor;

    public static BlockAttributeComponentImpl ofDefault() {
        return DEFAULT;
    }

    protected BlockAttributeComponentImpl(Function<BlockState, BlockAttributes> attributeAccessor) {
        this.attributeAccessor = attributeAccessor;
    }

    public static BlockAttributeComponentImpl ofGlobalStatic(BlockAttributes attributes) {
        return new BlockAttributeComponentImpl(state -> attributes);
    }

    public static BlockAttributeComponentImpl ofDirectDynamic(Function<BlockState, BlockAttributes> directAttributeAccessor) {
        return new BlockAttributeComponentImpl(directAttributeAccessor);
    }

    public static BlockAttributeComponentImpl ofCachedDynamic(Function<BlockState, BlockAttributes> directAttributeAccessor) {
        return new BlockAttributeComponentImpl(new CachedAttributeAccessor(directAttributeAccessor));
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHash(Supplier<Map<Integer, BlockAttributes>> attributeMapSupplier) {
        return ofMappedBlockStateHash(attributeMapSupplier.get());
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHash(Map<Integer, BlockAttributes> attributeMap) {
        return ofMappedBlockStateHash(attributeMap, BlockAttributes.DEFAULT);
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHash(Map<Integer, BlockAttributes> attributeMap, BlockAttributes defaultAttributes) {
        return new BlockAttributeComponentImpl(state -> attributeMap.getOrDefault(state.blockStateHash(), defaultAttributes));
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHashLazyLoad(Function<BlockType<?>, Map<Integer, BlockAttributes>> lazyLoader) {
        return ofDirectDynamic(new LazyLoaderAttributeAccessor(lazyLoader));
    }

    public static BlockAttributeComponentImpl ofRedefinedAABB(Function<BlockState, VoxelShape> aabbRedefiner) {
        return ofMappedBlockStateHashLazyLoad(blockType -> {
            var vanillaId = VanillaBlockId.fromIdentifier(blockType.getIdentifier());
            Objects.requireNonNull(vanillaId);
            var attributeMap = VanillaBlockAttributeRegistry.getRegistry().get(vanillaId);
            Objects.requireNonNull(attributeMap);
            var newAttributeMap = new HashMap<Integer, BlockAttributes>();
            attributeMap.forEach((blockStateHash, attribute) ->
                    newAttributeMap.put(
                            blockStateHash,
                            attribute
                                    .toBuilder()
                                    .voxelShape(aabbRedefiner.apply(Objects.requireNonNull(blockType.ofState(blockStateHash))))
                                    .build()
                    )
            );
            return newAttributeMap;
        });
    }

    @Override
    public BlockAttributes getBlockAttributes(BlockState blockState) {
        return attributeAccessor.apply(blockState);
    }

    private static class CachedAttributeAccessor implements Function<BlockState, BlockAttributes> {
        private final Map<Integer, BlockAttributes> attributeMap = new Int2ObjectNonBlockingMap<>();
        private final Function<BlockState, BlockAttributes> directAttributeAccessor;

        public CachedAttributeAccessor(Function<BlockState, BlockAttributes> directAttributeAccessor) {
            this.directAttributeAccessor = directAttributeAccessor;
        }

        @Override
        public BlockAttributes apply(BlockState blockState) {
            return attributeMap.computeIfAbsent(blockState.blockStateHash(), unused -> directAttributeAccessor.apply(blockState));
        }
    }

    private static class LazyLoaderAttributeAccessor implements Function<BlockState, BlockAttributes> {

        private Map<Integer, BlockAttributes> lazyLoadAttributeMap;
        private final Function<BlockType<?>, Map<Integer, BlockAttributes>> lazyLoader;

        public LazyLoaderAttributeAccessor(Function<BlockType<?>, Map<Integer, BlockAttributes>> lazyLoader) {
            this.lazyLoader = lazyLoader;
        }

        @Override
        public BlockAttributes apply(BlockState blockState) {
            if (lazyLoadAttributeMap == null) lazyLoadAttributeMap = lazyLoader.apply(blockState.getBlockType());
            return lazyLoadAttributeMap.get(blockState.blockStateHash());
        }
    }
}
