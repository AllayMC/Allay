package cn.allay.api.block.interfaces.brownshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockBrownShulkerBoxBehavior> BROWN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBrownShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_SHULKER_BOX)
            .build();
}
