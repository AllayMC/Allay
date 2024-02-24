package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTargetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTargetBehaviorInitializer {
  static void init() {
    BlockTypes.TARGET_TYPE = BlockTypeBuilder
            .builder(BlockTargetBehavior.class)
            .vanillaBlock(VanillaBlockId.TARGET)
            .build();
  }
}
