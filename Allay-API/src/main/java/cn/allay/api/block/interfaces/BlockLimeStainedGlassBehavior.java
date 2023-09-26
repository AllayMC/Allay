package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLimeStainedGlassBehavior extends BlockBehavior {
    BlockType<BlockLimeStainedGlassBehavior> LIME_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLimeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_STAINED_GLASS)
            .build();
}
