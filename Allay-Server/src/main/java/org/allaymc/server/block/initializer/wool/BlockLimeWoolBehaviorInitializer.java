package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockLimeWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeWoolBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockLimeWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_WOOL)
            .build();
  }
}
