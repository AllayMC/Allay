package org.allaymc.api.block.interfaces.stainedglass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardLightBlueStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockHardLightBlueStainedGlassBehavior> HARD_LIGHT_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardLightBlueStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_LIGHT_BLUE_STAINED_GLASS)
          .build();
}
