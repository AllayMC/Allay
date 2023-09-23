package cn.allay.api.block.interfaces.cyanshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockCyanShulkerBoxBehavior> CYAN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockCyanShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_SHULKER_BOX)
            .build();
}
