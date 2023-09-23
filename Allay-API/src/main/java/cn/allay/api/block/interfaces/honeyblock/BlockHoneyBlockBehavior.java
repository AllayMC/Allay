package cn.allay.api.block.interfaces.honeyblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHoneyBlockBehavior extends BlockBehavior {
    BlockType<BlockHoneyBlockBehavior> HONEY_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHoneyBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HONEY_BLOCK)
            .build();
}
