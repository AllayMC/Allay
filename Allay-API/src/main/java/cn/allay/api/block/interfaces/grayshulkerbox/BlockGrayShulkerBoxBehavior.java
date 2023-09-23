package cn.allay.api.block.interfaces.grayshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrayShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockGrayShulkerBoxBehavior> GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockGrayShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_SHULKER_BOX)
            .build();
}
