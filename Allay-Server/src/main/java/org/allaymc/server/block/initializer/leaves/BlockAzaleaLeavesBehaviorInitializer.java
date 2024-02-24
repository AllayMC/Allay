package org.allaymc.server.block.initializer.leaves;

import org.allaymc.api.block.interfaces.leaves.BlockAzaleaLeavesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAzaleaLeavesBehaviorInitializer {
  static void init() {
    BlockTypes.AZALEA_LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockAzaleaLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.AZALEA_LEAVES)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }
}
