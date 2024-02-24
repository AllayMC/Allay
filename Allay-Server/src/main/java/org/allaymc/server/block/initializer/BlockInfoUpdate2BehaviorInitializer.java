package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockInfoUpdate2Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdate2BehaviorInitializer {
  static void init() {
    BlockTypes.INFO_UPDATE2_TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdate2Behavior.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE2)
            .build();
  }
}
