package cn.allay.api.block.interfaces.purplecandle;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleCandleBehavior extends BlockBehavior {
    BlockType<BlockPurpleCandleBehavior> PURPLE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
}
