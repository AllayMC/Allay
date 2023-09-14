package cn.allay.api.block.interfaces;

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
public interface BlockCutCopperStairsBehavior extends BlockBehavior {
    BlockType<BlockCutCopperStairsBehavior> CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockCutCopperStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.CUT_COPPER_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION).addComponent(BlockAttributeComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
            .setBlockBaseComponentSupplier((b) -> new BlockBaseComponentImpl(b, null, null, null, CommonBlockPlaceFunctions.STAIR_PLACE, null, null, null))
            .build();
}
