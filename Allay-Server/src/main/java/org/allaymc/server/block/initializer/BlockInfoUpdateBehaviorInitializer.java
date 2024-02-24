package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockInfoUpdateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdateBehaviorInitializer {
  static void init() {
    BlockTypes.INFO_UPDATE_TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdateBehavior.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE)
            .build();
  }
}
