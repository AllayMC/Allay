package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMudBricksBehavior extends BlockBehavior {
  BlockType<BlockMudBricksBehavior> MUD_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockMudBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICKS)
          .build();
}
