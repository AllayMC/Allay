package cn.allay.api.block.component;

import cn.allay.api.block.component.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.component.attribute.BlockAttributes;
import cn.allay.api.block.component.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.data.VanillaBlockId;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

import static cn.allay.api.math.voxelshape.CommonShapes.buildStairShape;

/**
 * Allay Project 2023/9/2
 *
 * @author daoge_cmd
 */
public interface CommonBlockComponents {
    Supplier<BlockAttributeComponentImpl> STAIR_ATTRIBUTE_COMPONENT_SUPPLIER =
            () -> BlockAttributeComponentImpl.ofMappedBlockStateHashLazyLoad(blockType -> {
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
                                        .voxelShape(buildStairShape(
                                                Objects.requireNonNull(blockType.ofState(blockStateHash))))
                                        .build()
                        )
                );
                return newAttributeMap;
            });
}
