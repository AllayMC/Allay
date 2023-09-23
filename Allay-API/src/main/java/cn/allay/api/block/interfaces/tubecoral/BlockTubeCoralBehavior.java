package cn.allay.api.block.interfaces.tubecoral;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTubeCoralBehavior extends BlockBehavior {
    BlockType<BlockTubeCoralBehavior> TUBE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockTubeCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.TUBE_CORAL)
            .build();
}
