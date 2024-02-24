package org.allaymc.server.block.initializer.leaves;

import org.allaymc.api.block.interfaces.leaves.BlockMangroveLeavesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveLeavesBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockMangroveLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }
}
