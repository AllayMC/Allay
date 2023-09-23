package cn.allay.api.block.interfaces.sprucestairs;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.CommonBlockPlaceFunctions;
import cn.allay.api.block.component.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.component.base.BlockBaseComponentImpl;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.math.voxelshape.CommonShapes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSpruceStairsBehavior extends BlockBehavior {
    BlockType<BlockSpruceStairsBehavior> SPRUCE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSpruceStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION).addComponent(BlockAttributeComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
            .setBlockBaseComponentSupplier((b) -> BlockBaseComponentImpl.builder().place(CommonBlockPlaceFunctions.STAIR_PLACE).build())
            .build();
}
