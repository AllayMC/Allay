package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedStainedGlassPaneBehavior extends BlockBehavior {
    BlockType<BlockRedStainedGlassPaneBehavior> RED_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockRedStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_STAINED_GLASS_PANE)
            .build();
}
