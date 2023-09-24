package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenShulkerBoxBehavior extends BlockBehavior {
    BlockType<BlockGreenShulkerBoxBehavior> GREEN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockGreenShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX)
            .build();
}
