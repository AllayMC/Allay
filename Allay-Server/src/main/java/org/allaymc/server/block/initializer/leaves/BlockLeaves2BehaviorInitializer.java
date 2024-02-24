package org.allaymc.server.block.initializer.leaves;

import org.allaymc.api.block.interfaces.leaves.BlockLeaves2Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLeaves2BehaviorInitializer {
  static void init() {
    BlockTypes.LEAVES2_TYPE = BlockTypeBuilder
            .builder(BlockLeaves2Behavior.class)
            .vanillaBlock(VanillaBlockId.LEAVES2)
            .setProperties(VanillaBlockPropertyTypes.NEW_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }
}
