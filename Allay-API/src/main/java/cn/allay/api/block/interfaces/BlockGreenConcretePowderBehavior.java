package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenConcretePowderBehavior extends BlockBehavior {
    BlockType<BlockGreenConcretePowderBehavior> GREEN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockGreenConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CONCRETE_POWDER)
            .build();
}
