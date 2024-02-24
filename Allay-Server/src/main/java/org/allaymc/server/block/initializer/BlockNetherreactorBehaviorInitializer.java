package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetherreactorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherreactorBehaviorInitializer {
  static void init() {
    BlockTypes.NETHERREACTOR_TYPE = BlockTypeBuilder
            .builder(BlockNetherreactorBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHERREACTOR)
            .build();
  }
}
