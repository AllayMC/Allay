package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkStainedGlassPaneBehavior extends BlockBehavior {
    BlockType<BlockPinkStainedGlassPaneBehavior> PINK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockPinkStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_STAINED_GLASS_PANE)
            .build();
}
