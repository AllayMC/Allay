package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCopperBlockBehavior extends BlockBehavior {
    BlockType<BlockCopperBlockBehavior> COPPER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCopperBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_BLOCK)
            .build();
}
