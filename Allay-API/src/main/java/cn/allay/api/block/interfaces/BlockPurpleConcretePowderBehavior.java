package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleConcretePowderBehavior extends BlockBehavior {
    BlockType<BlockPurpleConcretePowderBehavior> PURPLE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockPurpleConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE_POWDER)
            .build();
}
