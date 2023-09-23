package cn.allay.api.block.interfaces.greenconcrete;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenConcreteBehavior extends BlockBehavior {
    BlockType<BlockGreenConcreteBehavior> GREEN_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockGreenConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CONCRETE)
            .build();
}
