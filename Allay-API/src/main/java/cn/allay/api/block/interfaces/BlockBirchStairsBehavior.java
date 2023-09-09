package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

import static cn.allay.api.block.component.CommonBlockComponents.STAIR_ATTRIBUTE_COMPONENT_SUPPLIER;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBirchStairsBehavior extends BlockBehavior {
    BlockType<BlockBirchStairsBehavior> BIRCH_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockBirchStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION).addComponent(STAIR_ATTRIBUTE_COMPONENT_SUPPLIER.get()).build();
}
