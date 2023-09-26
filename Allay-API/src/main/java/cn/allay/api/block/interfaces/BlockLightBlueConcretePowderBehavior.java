package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueConcretePowderBehavior extends BlockBehavior {
    BlockType<BlockLightBlueConcretePowderBehavior> LIGHT_BLUE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE_POWDER)
            .build();
}
