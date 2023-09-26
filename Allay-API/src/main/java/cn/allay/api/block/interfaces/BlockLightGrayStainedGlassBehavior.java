package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayStainedGlassBehavior extends BlockBehavior {
    BlockType<BlockLightGrayStainedGlassBehavior> LIGHT_GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_STAINED_GLASS)
            .build();
}
