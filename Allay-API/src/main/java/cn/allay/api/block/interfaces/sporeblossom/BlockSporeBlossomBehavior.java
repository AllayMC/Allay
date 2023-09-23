package cn.allay.api.block.interfaces.sporeblossom;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSporeBlossomBehavior extends BlockBehavior {
    BlockType<BlockSporeBlossomBehavior> SPORE_BLOSSOM_TYPE = BlockTypeBuilder
            .builder(BlockSporeBlossomBehavior.class)
            .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM)
            .build();
}
