package cn.allay.api.block.interfaces.lightbluecarpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueCarpetBehavior extends BlockBehavior {
    BlockType<BlockLightBlueCarpetBehavior> LIGHT_BLUE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CARPET)
            .build();
}
