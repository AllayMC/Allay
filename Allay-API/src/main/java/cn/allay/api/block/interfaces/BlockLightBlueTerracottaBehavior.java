package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueTerracottaBehavior extends BlockBehavior {
    BlockType<BlockLightBlueTerracottaBehavior> LIGHT_BLUE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_TERRACOTTA)
            .build();
}
