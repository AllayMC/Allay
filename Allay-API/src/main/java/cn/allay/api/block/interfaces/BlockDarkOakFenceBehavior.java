package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakFenceBehavior extends BlockBehavior {
    BlockType<BlockDarkOakFenceBehavior> DARK_OAK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE)
            .build();
}
