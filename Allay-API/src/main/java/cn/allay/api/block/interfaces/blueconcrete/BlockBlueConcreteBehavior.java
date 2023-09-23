package cn.allay.api.block.interfaces.blueconcrete;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueConcreteBehavior extends BlockBehavior {
    BlockType<BlockBlueConcreteBehavior> BLUE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBlueConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CONCRETE)
            .build();
}
