package cn.allay.block.component.impl.attribute;

import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.component.impl.base.BlockBaseComponent;
import cn.allay.block.property.state.BlockState;
import cn.allay.component.annotation.Dependency;
import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import lombok.Builder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
public class BlockAttributeComponentImpl implements BlockAttributeComponent, BlockComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_attribute_component");

    @Dependency
    protected BlockBaseComponent baseComponent;
    protected Function<BlockState<?>, BlockAttributes> attributeAccessor;

    protected BlockAttributeComponentImpl(Function<BlockState<?>, BlockAttributes> attributeAccessor) {
        this.attributeAccessor = attributeAccessor;
    }

    public static BlockAttributeComponentImpl ofGlobalStatic(BlockAttributes attributes) {
        return new BlockAttributeComponentImpl(state -> attributes);
    }

    public static BlockAttributeComponentImpl ofDynamic(Function<BlockState<?>, BlockAttributes> attributeAccessor) {
        return new BlockAttributeComponentImpl(attributeAccessor);
    }

    public static BlockAttributeComponentImpl ofMapped(Map<BlockState<?>, BlockAttributes> attributeMap) {
        return ofMapped(attributeMap, BlockAttributes.DEFAULT);
    }

    public static BlockAttributeComponentImpl ofMapped(Map<BlockState<?>, BlockAttributes> attributeMap, BlockAttributes defaultAttributes) {
        return new BlockAttributeComponentImpl(state -> attributeMap.getOrDefault(state, defaultAttributes));
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHash(Map<Integer, BlockAttributes> attributeMap) {
        return ofMappedBlockStateHash(attributeMap, BlockAttributes.DEFAULT);
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHash(Map<Integer, BlockAttributes> attributeMap, BlockAttributes defaultAttributes) {
        return new BlockAttributeComponentImpl(state -> attributeMap.getOrDefault(state.getBlockStateHash(), defaultAttributes));
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public BlockAttributes getBlockAttributes() {
        return attributeAccessor.apply(baseComponent.getCurrentState());
    }
}
