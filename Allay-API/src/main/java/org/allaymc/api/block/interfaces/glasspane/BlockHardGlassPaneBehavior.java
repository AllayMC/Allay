package org.allaymc.api.block.interfaces.glasspane;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardGlassPaneBehavior extends BlockBehavior {
  BlockType<BlockHardGlassPaneBehavior> HARD_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE)
          .build();
}
