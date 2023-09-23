package cn.allay.api.block.interfaces.hardglasspane;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardGlassPaneBehavior extends BlockBehavior {
    BlockType<BlockHardGlassPaneBehavior> HARD_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE)
            .build();
}
