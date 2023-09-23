package cn.allay.api.block.interfaces.diamondblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDiamondBlockBehavior extends BlockBehavior {
    BlockType<BlockDiamondBlockBehavior> DIAMOND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDiamondBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DIAMOND_BLOCK)
            .build();
}
