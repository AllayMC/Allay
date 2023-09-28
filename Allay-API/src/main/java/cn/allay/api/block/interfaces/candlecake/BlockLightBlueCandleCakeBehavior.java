package cn.allay.api.block.interfaces.candlecake;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueCandleCakeBehavior extends BlockBehavior {
  BlockType<BlockLightBlueCandleCakeBehavior> LIGHT_BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCandleCakeBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE_CAKE)
          .setProperties(VanillaBlockPropertyTypes.LIT)
          .build();
}
