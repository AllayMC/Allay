package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateDiamondOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateDiamondOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_DIAMOND_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateDiamondOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE)
            .build();
  }
}
