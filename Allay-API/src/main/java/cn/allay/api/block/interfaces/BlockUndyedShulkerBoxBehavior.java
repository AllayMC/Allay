package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockUndyedShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockUndyedShulkerBoxBehavior> UNDYED_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockUndyedShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX)
            .build();
}
