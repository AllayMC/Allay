package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChainCommandBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChainCommandBlockBehaviorInitializer {
  static void init() {
    BlockTypes.CHAIN_COMMAND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockChainCommandBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.CHAIN_COMMAND_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
