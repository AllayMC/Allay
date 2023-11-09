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
public interface BlockCaveVinesBehavior extends BlockBehavior {
  BlockType<BlockCaveVinesBehavior> CAVE_VINES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES)
          .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .build();
}
