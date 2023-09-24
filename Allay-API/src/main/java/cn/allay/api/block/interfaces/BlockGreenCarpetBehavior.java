package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenCarpetBehavior extends BlockBehavior {
    BlockType<BlockGreenCarpetBehavior> GREEN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockGreenCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CARPET)
            .build();
}
