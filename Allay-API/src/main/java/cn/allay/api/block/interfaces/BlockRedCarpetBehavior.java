package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedCarpetBehavior extends BlockBehavior {
    BlockType<BlockRedCarpetBehavior> RED_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockRedCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CARPET)
            .build();
}
