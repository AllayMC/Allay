package cn.allay.api.block.interfaces.oakfence;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOakFenceBehavior extends BlockBehavior {
    BlockType<BlockOakFenceBehavior> OAK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockOakFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_FENCE)
            .build();
}
