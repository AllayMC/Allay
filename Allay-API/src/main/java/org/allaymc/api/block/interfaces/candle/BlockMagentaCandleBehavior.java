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
public interface BlockMagentaCandleBehavior extends BlockBehavior {
  BlockType<BlockMagentaCandleBehavior> MAGENTA_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCandleBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE)
          .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
          .build();
}
