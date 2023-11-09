package org.allaymc.api.block.interfaces.glasspane;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGlassPaneBehavior extends BlockBehavior {
  BlockType<BlockGlassPaneBehavior> GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.GLASS_PANE)
          .build();
}
