package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSnowLayerBehavior extends BlockBehavior {
  BlockType<BlockSnowLayerBehavior> SNOW_LAYER_TYPE = BlockTypeBuilder
          .builder(BlockSnowLayerBehavior.class)
          .vanillaBlock(VanillaBlockId.SNOW_LAYER)
          .setProperties(VanillaBlockPropertyTypes.COVERED_BIT, VanillaBlockPropertyTypes.HEIGHT)
          .build();
}
