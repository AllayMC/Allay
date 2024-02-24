package org.allaymc.server.block.initializer.leaves;

import org.allaymc.api.block.interfaces.leaves.BlockAzaleaLeavesFloweredBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAzaleaLeavesFloweredBehaviorInitializer {
  static void init() {
    BlockTypes.AZALEA_LEAVES_FLOWERED_TYPE = BlockTypeBuilder
            .builder(BlockAzaleaLeavesFloweredBehavior.class)
            .vanillaBlock(VanillaBlockId.AZALEA_LEAVES_FLOWERED)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }
}
