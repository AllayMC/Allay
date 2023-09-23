package cn.allay.api.block.interfaces.smoker;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSmokerBehavior extends BlockBehavior {
    BlockType<BlockSmokerBehavior> SMOKER_TYPE = BlockTypeBuilder
            .builder(BlockSmokerBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOKER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
