package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

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
