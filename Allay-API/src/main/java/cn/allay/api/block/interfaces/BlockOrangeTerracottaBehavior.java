package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOrangeTerracottaBehavior extends BlockBehavior {
    BlockType<BlockOrangeTerracottaBehavior> ORANGE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockOrangeTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_TERRACOTTA)
            .build();
}
