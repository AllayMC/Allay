package cn.allay.api.block.interfaces.candle;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueCandleBehavior extends BlockBehavior {
  BlockType<BlockBlueCandleBehavior> BLUE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBlueCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .build();
}
