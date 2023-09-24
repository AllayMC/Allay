package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlackConcreteBehavior extends BlockBehavior {
    BlockType<BlockBlackConcreteBehavior> BLACK_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBlackConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CONCRETE)
            .build();
}
