package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStonecutterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStonecutterBehaviorInitializer {
  static void init() {
    BlockTypes.STONECUTTER_TYPE = BlockTypeBuilder
            .builder(BlockStonecutterBehavior.class)
            .vanillaBlock(VanillaBlockId.STONECUTTER)
            .build();
  }
}
