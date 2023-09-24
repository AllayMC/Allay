package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMudBricksBehavior extends BlockBehavior {
    BlockType<BlockMudBricksBehavior> MUD_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockMudBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICKS)
            .build();
}
