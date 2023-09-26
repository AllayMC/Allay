package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenStainedGlassPaneBehavior extends BlockBehavior {
    BlockType<BlockGreenStainedGlassPaneBehavior> GREEN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockGreenStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_STAINED_GLASS_PANE)
            .build();
}
