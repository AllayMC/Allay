package cn.allay.api.block.interfaces.whitecarpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWhiteCarpetBehavior extends BlockBehavior {
    BlockType<BlockWhiteCarpetBehavior> WHITE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockWhiteCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CARPET)
            .build();
}
