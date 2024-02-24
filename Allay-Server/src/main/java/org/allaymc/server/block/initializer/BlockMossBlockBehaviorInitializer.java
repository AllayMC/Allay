package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMossBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMossBlockBehaviorInitializer {
  static void init() {
    BlockTypes.MOSS_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMossBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSS_BLOCK)
            .build();
  }
}
