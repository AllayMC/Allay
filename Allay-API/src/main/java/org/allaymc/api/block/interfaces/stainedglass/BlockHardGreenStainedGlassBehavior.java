package org.allaymc.api.block.interfaces.stainedglass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardGreenStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockHardGreenStainedGlassBehavior> HARD_GREEN_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardGreenStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_GREEN_STAINED_GLASS)
          .build();
}
