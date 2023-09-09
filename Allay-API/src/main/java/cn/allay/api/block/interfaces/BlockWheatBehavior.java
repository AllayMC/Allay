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
public interface BlockWheatBehavior extends BlockBehavior {
    BlockType<BlockWheatBehavior> WHEAT_TYPE = BlockTypeBuilder
            .builder(BlockWheatBehavior.class)
            .vanillaBlock(VanillaBlockId.WHEAT)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
}
