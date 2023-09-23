package cn.allay.api.block.interfaces.birchfence;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBirchFenceBehavior extends BlockBehavior {
    BlockType<BlockBirchFenceBehavior> BIRCH_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockBirchFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_FENCE)
            .build();
}
