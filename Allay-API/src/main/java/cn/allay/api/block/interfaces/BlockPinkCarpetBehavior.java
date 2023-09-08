package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkCarpetBehavior extends BlockBehavior {
    BlockType<BlockPinkCarpetBehavior> PINK_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockPinkCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CARPET)
            .build();
}
