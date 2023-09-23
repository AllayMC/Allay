package cn.allay.api.block.interfaces.horncoral;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHornCoralBehavior extends BlockBehavior {
    BlockType<BlockHornCoralBehavior> HORN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockHornCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.HORN_CORAL)
            .build();
}
