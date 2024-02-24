package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStrippedBambooBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedBambooBlockBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_BAMBOO_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStrippedBambooBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BAMBOO_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
