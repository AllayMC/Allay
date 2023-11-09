package org.allaymc.api.block.interfaces.glass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardGlassBehavior extends BlockBehavior {
  BlockType<BlockHardGlassBehavior> HARD_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS)
          .build();
}
