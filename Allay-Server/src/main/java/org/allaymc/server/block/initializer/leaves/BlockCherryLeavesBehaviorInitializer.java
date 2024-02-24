package org.allaymc.server.block.initializer.leaves;

import org.allaymc.api.block.interfaces.leaves.BlockCherryLeavesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryLeavesBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockCherryLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_LEAVES)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }
}
