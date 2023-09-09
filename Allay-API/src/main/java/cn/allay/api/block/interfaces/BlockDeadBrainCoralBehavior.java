package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeadBrainCoralBehavior extends BlockBehavior {
    BlockType<BlockDeadBrainCoralBehavior> DEAD_BRAIN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadBrainCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_BRAIN_CORAL)
            .build();
}
