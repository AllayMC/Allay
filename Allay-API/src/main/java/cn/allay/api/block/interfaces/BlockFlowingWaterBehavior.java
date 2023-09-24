package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFlowingWaterBehavior extends BlockBehavior {
    BlockType<BlockFlowingWaterBehavior> FLOWING_WATER_TYPE = BlockTypeBuilder
            .builder(BlockFlowingWaterBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWING_WATER)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
}
