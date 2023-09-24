package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveFenceBehavior extends BlockBehavior {
    BlockType<BlockMangroveFenceBehavior> MANGROVE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockMangroveFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_FENCE)
            .build();
}
