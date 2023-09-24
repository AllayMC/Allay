package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSmoothStoneBehavior extends BlockBehavior {
    BlockType<BlockSmoothStoneBehavior> SMOOTH_STONE_TYPE = BlockTypeBuilder
            .builder(BlockSmoothStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_STONE)
            .build();
}
