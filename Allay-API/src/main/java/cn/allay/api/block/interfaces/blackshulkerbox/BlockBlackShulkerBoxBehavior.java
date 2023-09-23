package cn.allay.api.block.interfaces.blackshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlackShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockBlackShulkerBoxBehavior> BLACK_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBlackShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_SHULKER_BOX)
            .build();
}
