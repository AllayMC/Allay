package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateLapisOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateLapisOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_LAPIS_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateLapisOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_LAPIS_ORE)
            .build();
  }
}
