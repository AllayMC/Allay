package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedTrapdoorBehavior extends BlockBehavior {
    BlockType<BlockWarpedTrapdoorBehavior> WARPED_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWarpedTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
}
