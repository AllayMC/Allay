package cn.allay.api.block.interfaces.movingblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMovingBlockBehavior extends BlockBehavior {
    BlockType<BlockMovingBlockBehavior> MOVING_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMovingBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MOVING_BLOCK)
            .build();
}
