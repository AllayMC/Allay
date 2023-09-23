package cn.allay.api.block.interfaces.lightgraycarpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayCarpetBehavior extends BlockBehavior {
    BlockType<BlockLightGrayCarpetBehavior> LIGHT_GRAY_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CARPET)
            .build();
}
