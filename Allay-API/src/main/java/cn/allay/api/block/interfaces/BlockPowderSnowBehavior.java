package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPowderSnowBehavior extends BlockBehavior {
    BlockType<BlockPowderSnowBehavior> POWDER_SNOW_TYPE = BlockTypeBuilder
            .builder(BlockPowderSnowBehavior.class)
            .vanillaBlock(VanillaBlockId.POWDER_SNOW)
            .build();
}
