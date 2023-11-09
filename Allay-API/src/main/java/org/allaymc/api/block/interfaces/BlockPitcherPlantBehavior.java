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
public interface BlockPitcherPlantBehavior extends BlockBehavior {
  BlockType<BlockPitcherPlantBehavior> PITCHER_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockPitcherPlantBehavior.class)
          .vanillaBlock(VanillaBlockId.PITCHER_PLANT)
          .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .build();
}
