package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedDeepslateBricksBehavior extends BlockBehavior {
    BlockType<BlockCrackedDeepslateBricksBehavior> CRACKED_DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockCrackedDeepslateBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_BRICKS)
            .build();
}
