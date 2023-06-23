package cn.allay.api.block.component.impl.attribute;

import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.base.BlockBaseComponent;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;

import java.util.Map;
import java.util.function.Function;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/25 <br>
 * Allay Project <br>
 */
public class BlockAttributeComponentImpl implements BlockAttributeComponent, BlockComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_attribute_component");

    @Dependency
    protected BlockBaseComponent baseComponent;
    protected Function<BlockState, BlockAttributes> attributeAccessor;

    protected BlockAttributeComponentImpl(Function<BlockState, BlockAttributes> attributeAccessor) {
        this.attributeAccessor = attributeAccessor;
    }

    public static BlockAttributeComponentImpl ofGlobalStatic(BlockAttributes attributes) {
        return new BlockAttributeComponentImpl(state -> attributes);
    }

    public static BlockAttributeComponentImpl ofDynamic(Function<BlockState, BlockAttributes> attributeAccessor) {
        return new BlockAttributeComponentImpl(attributeAccessor);
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHash(Map<Integer, BlockAttributes> attributeMap) {
        return ofMappedBlockStateHash(attributeMap, BlockAttributes.DEFAULT);
    }

    public static BlockAttributeComponentImpl ofMappedBlockStateHash(Map<Integer, BlockAttributes> attributeMap, BlockAttributes defaultAttributes) {
        return new BlockAttributeComponentImpl(state -> attributeMap.getOrDefault(state.blockStateHash(), defaultAttributes));
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public BlockAttributes getBlockAttributes() {
        return attributeAccessor.apply(baseComponent.getCurrentState());
    }
}
