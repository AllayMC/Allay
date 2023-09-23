package cn.allay.api.block.interfaces.rawironblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRawIronBlockBehavior extends BlockBehavior {
    BlockType<BlockRawIronBlockBehavior> RAW_IRON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRawIronBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RAW_IRON_BLOCK)
            .build();
}
