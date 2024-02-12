package org.allaymc.api.block.interfaces.stainedglass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardPinkStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockHardPinkStainedGlassBehavior> HARD_PINK_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardPinkStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_PINK_STAINED_GLASS)
          .build();
}
