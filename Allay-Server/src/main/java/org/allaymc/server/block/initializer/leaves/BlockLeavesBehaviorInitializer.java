package org.allaymc.server.block.initializer.leaves;

import org.allaymc.api.block.interfaces.leaves.BlockLeavesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLeavesBehaviorInitializer {
  static void init() {
    BlockTypes.LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.LEAVES)
            .setProperties(VanillaBlockPropertyTypes.OLD_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }
}
