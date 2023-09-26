package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueConcretePowderBehavior extends BlockBehavior {
    BlockType<BlockBlueConcretePowderBehavior> BLUE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockBlueConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CONCRETE_POWDER)
            .build();
}
