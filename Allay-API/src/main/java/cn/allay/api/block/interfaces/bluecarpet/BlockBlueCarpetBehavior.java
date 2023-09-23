package cn.allay.api.block.interfaces.bluecarpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueCarpetBehavior extends BlockBehavior {
    BlockType<BlockBlueCarpetBehavior> BLUE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockBlueCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CARPET)
            .build();
}
