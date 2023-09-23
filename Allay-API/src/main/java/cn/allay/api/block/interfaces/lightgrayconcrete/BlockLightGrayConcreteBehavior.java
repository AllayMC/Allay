package cn.allay.api.block.interfaces.lightgrayconcrete;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayConcreteBehavior extends BlockBehavior {
    BlockType<BlockLightGrayConcreteBehavior> LIGHT_GRAY_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CONCRETE)
            .build();
}
