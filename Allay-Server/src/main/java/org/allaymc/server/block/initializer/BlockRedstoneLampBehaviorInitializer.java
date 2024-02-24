package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedstoneLampBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedstoneLampBehaviorInitializer {
  static void init() {
    BlockTypes.REDSTONE_LAMP_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneLampBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_LAMP)
            .build();
  }
}
