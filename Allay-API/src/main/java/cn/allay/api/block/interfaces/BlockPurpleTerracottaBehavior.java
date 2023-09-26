package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleTerracottaBehavior extends BlockBehavior {
    BlockType<BlockPurpleTerracottaBehavior> PURPLE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPurpleTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_TERRACOTTA)
            .build();
}
