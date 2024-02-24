package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeadbushBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeadbushBehaviorInitializer {
  static void init() {
    BlockTypes.DEADBUSH_TYPE = BlockTypeBuilder
            .builder(BlockDeadbushBehavior.class)
            .vanillaBlock(VanillaBlockId.DEADBUSH)
            .build();
  }
}
