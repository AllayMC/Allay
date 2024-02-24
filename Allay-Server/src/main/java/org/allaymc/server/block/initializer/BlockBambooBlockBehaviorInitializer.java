package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBambooBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooBlockBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBambooBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
