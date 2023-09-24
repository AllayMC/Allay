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
public interface BlockLightGrayCandleBehavior extends BlockBehavior {
    BlockType<BlockLightGrayCandleBehavior> LIGHT_GRAY_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
}
