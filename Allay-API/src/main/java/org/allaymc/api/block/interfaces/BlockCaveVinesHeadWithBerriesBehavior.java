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
public interface BlockCaveVinesHeadWithBerriesBehavior extends BlockBehavior {
  BlockType<BlockCaveVinesHeadWithBerriesBehavior> CAVE_VINES_HEAD_WITH_BERRIES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES)
          .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .build();
}
