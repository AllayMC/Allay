package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMagentaStainedGlassPaneBehavior extends BlockBehavior {
    BlockType<BlockMagentaStainedGlassPaneBehavior> MAGENTA_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_STAINED_GLASS_PANE)
            .build();
}
