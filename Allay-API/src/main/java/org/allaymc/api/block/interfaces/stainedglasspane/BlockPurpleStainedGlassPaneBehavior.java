package org.allaymc.api.block.interfaces.stainedglasspane;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleStainedGlassPaneBehavior extends BlockBehavior {
  BlockType<BlockPurpleStainedGlassPaneBehavior> PURPLE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_STAINED_GLASS_PANE)
          .build();
}
