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
public interface BlockGreenCandleBehavior extends BlockBehavior {
  BlockType<BlockGreenCandleBehavior> GREEN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .build();
}
