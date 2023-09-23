package cn.allay.api.block.interfaces.sprucefence;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSpruceFenceBehavior extends BlockBehavior {
    BlockType<BlockSpruceFenceBehavior> SPRUCE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockSpruceFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_FENCE)
            .build();
}
