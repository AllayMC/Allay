package cn.allay.api.block.interfaces.orangeshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOrangeShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockOrangeShulkerBoxBehavior> ORANGE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockOrangeShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_SHULKER_BOX)
            .build();
}
