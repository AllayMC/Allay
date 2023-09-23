package cn.allay.api.block.interfaces.pinkshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockPinkShulkerBoxBehavior> PINK_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockPinkShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_SHULKER_BOX)
            .build();
}
