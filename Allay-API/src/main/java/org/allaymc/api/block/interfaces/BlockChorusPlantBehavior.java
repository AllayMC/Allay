package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChorusPlantBehavior extends BlockBehavior {
  BlockType<BlockChorusPlantBehavior> CHORUS_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockChorusPlantBehavior.class)
          .vanillaBlock(VanillaBlockId.CHORUS_PLANT)
          .build();
}
