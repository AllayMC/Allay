package cn.allay.api.block.component.attribute;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import cn.allay.api.identifier.Identifier;

import java.util.Map;
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

    protected Function<BlockState, BlockAttributes> attributeAccessor;

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

    @Override
    @Impl
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
            if (lazyLoadAttributeMap == null) lazyLoadAttributeMap = lazyLoader.apply(blockState.blockType());
            return lazyLoadAttributeMap.get(blockState.blockStateHash());
        }
    }
}
