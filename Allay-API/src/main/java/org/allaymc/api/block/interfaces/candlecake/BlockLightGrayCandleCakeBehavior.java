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
public interface BlockLightGrayCandleCakeBehavior extends BlockBehavior {
  BlockType<BlockLightGrayCandleCakeBehavior> LIGHT_GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .build();
}
