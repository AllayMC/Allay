package cn.allay.api.block.interfaces.yellowcandle;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowCandleBehavior extends BlockBehavior {
    BlockType<BlockYellowCandleBehavior> YELLOW_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockYellowCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
}
