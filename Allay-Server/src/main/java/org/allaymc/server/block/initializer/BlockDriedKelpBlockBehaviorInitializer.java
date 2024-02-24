package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDriedKelpBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDriedKelpBlockBehaviorInitializer {
  static void init() {
    BlockTypes.DRIED_KELP_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDriedKelpBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK)
            .build();
  }
}
