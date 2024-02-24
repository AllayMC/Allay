package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockMagentaConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CONCRETE)
            .build();
  }
}
