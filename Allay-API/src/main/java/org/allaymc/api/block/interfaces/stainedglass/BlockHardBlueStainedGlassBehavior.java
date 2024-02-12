package org.allaymc.api.block.interfaces.stainedglass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardBlueStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockHardBlueStainedGlassBehavior> HARD_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardBlueStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_BLUE_STAINED_GLASS)
          .build();
}
