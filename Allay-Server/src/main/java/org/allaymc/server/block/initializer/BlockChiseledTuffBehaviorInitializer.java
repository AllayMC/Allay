package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChiseledTuffBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledTuffBehaviorInitializer {
  static void init() {
    BlockTypes.CHISELED_TUFF_TYPE = BlockTypeBuilder
            .builder(BlockChiseledTuffBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_TUFF)
            .build();
  }
}
