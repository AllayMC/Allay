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
public interface BlockDioriteStairsBehavior extends BlockBehavior {
    BlockType<BlockDioriteStairsBehavior> DIORITE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockDioriteStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.DIORITE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION).addComponent(STAIR_ATTRIBUTE_COMPONENT_SUPPLIER.get()).build();
}
