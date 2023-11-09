package org.allaymc.api.block.interfaces.stainedglass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWhiteStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockWhiteStainedGlassBehavior> WHITE_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockWhiteStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_STAINED_GLASS)
          .build();
}
