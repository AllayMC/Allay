package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateCopperOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateCopperOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_COPPER_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateCopperOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE)
            .build();
  }
}
