package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDripstoneBlockBehavior extends BlockBehavior {
    BlockType<BlockDripstoneBlockBehavior> DRIPSTONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDripstoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK)
            .build();
}
