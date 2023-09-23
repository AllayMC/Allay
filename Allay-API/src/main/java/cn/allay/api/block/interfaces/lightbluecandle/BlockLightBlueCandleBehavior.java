package cn.allay.api.block.interfaces.lightbluecandle;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueCandleBehavior extends BlockBehavior {
    BlockType<BlockLightBlueCandleBehavior> LIGHT_BLUE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
}
