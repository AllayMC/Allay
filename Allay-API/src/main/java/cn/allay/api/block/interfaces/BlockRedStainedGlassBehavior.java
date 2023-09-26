package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedStainedGlassBehavior extends BlockBehavior {
    BlockType<BlockRedStainedGlassBehavior> RED_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockRedStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_STAINED_GLASS)
            .build();
}
