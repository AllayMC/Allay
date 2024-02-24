package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLitRedstoneLampBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitRedstoneLampBehaviorInitializer {
  static void init() {
    BlockTypes.LIT_REDSTONE_LAMP_TYPE = BlockTypeBuilder
            .builder(BlockLitRedstoneLampBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_REDSTONE_LAMP)
            .build();
  }
}
