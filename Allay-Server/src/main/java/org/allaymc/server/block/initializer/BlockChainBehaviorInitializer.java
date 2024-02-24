package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChainBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChainBehaviorInitializer {
  static void init() {
    BlockTypes.CHAIN_TYPE = BlockTypeBuilder
            .builder(BlockChainBehavior.class)
            .vanillaBlock(VanillaBlockId.CHAIN)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
