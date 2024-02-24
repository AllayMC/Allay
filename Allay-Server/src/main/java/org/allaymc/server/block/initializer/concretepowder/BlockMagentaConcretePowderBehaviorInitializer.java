package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockMagentaConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockMagentaConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CONCRETE_POWDER)
            .build();
  }
}
