package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedDeepslateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedDeepslateBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE)
            .build();
  }
}
