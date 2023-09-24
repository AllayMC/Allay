package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLapisBlockBehavior extends BlockBehavior {
    BlockType<BlockLapisBlockBehavior> LAPIS_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockLapisBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.LAPIS_BLOCK)
            .build();
}
