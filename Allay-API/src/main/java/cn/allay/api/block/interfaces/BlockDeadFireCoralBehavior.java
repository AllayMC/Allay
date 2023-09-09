package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeadFireCoralBehavior extends BlockBehavior {
    BlockType<BlockDeadFireCoralBehavior> DEAD_FIRE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadFireCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_FIRE_CORAL)
            .build();
}
