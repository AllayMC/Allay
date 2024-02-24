package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBambooBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_TYPE = BlockTypeBuilder
            .builder(BlockBambooBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE, VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
            .build();
  }
}
