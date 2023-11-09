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
public interface BlockFlowingWaterBehavior extends BlockBehavior {
  BlockType<BlockFlowingWaterBehavior> FLOWING_WATER_TYPE = BlockTypeBuilder
          .builder(BlockFlowingWaterBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWING_WATER)
          .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .build();
}
