package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockChiseledTuffBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledTuffBricksBehaviorInitializer {
  static void init() {
    BlockTypes.CHISELED_TUFF_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockChiseledTuffBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_TUFF_BRICKS)
            .build();
  }
}
