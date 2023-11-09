package org.allaymc.api.block.interfaces.candlecake;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenCandleCakeBehavior extends BlockBehavior {
  BlockType<BlockGreenCandleCakeBehavior> GREEN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .build();
}
