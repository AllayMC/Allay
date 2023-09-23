package cn.allay.api.block.interfaces.endbricks;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockEndBricksBehavior extends BlockBehavior {
    BlockType<BlockEndBricksBehavior> END_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockEndBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.END_BRICKS)
            .build();
}
