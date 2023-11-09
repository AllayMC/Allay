package org.allaymc.api.block.interfaces.candle;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOrangeCandleBehavior extends BlockBehavior {
  BlockType<BlockOrangeCandleBehavior> ORANGE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .build();
}
