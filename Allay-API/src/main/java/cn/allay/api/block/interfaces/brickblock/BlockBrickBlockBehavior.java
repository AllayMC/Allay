package cn.allay.api.block.interfaces.brickblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrickBlockBehavior extends BlockBehavior {
    BlockType<BlockBrickBlockBehavior> BRICK_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBrickBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BRICK_BLOCK)
            .build();
}
