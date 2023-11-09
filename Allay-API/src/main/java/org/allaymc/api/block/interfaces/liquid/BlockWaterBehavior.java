package org.allaymc.api.block.interfaces.liquid;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaterBehavior extends BlockBehavior {
  BlockType<BlockWaterBehavior> WATER_TYPE = BlockTypeBuilder
          .builder(BlockWaterBehavior.class)
          .vanillaBlock(VanillaBlockId.WATER)
          .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .build();
}
