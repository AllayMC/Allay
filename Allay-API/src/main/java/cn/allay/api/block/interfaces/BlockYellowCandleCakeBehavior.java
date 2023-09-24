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
public interface BlockYellowCandleCakeBehavior extends BlockBehavior {
    BlockType<BlockYellowCandleCakeBehavior> YELLOW_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockYellowCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
}
