package cn.allay.api.block.interfaces.cyancarpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanCarpetBehavior extends BlockBehavior {
    BlockType<BlockCyanCarpetBehavior> CYAN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockCyanCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CARPET)
            .build();
}
