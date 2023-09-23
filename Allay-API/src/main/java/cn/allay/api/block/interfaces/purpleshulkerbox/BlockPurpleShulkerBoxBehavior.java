package cn.allay.api.block.interfaces.purpleshulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockPurpleShulkerBoxBehavior> PURPLE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockPurpleShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_SHULKER_BOX)
            .build();
}
