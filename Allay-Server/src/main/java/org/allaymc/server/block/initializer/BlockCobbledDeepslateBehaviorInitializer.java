package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCobbledDeepslateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCobbledDeepslateBehaviorInitializer {
  static void init() {
    BlockTypes.COBBLED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE)
            .build();
  }
}
