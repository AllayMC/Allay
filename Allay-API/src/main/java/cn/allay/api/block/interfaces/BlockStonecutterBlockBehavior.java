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
public interface BlockStonecutterBlockBehavior extends BlockBehavior {
    BlockType<BlockStonecutterBlockBehavior> STONECUTTER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStonecutterBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STONECUTTER_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
