package cn.allay.api.block.interfaces.browncandle;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownCandleBehavior extends BlockBehavior {
    BlockType<BlockBrownCandleBehavior> BROWN_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockBrownCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
}
