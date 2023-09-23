package cn.allay.api.block.interfaces.limeshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLimeShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockLimeShulkerBoxBehavior> LIME_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockLimeShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_SHULKER_BOX)
            .build();
}
