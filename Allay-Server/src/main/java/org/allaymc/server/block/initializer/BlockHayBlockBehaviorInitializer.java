package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockHayBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHayBlockBehaviorInitializer {
  static void init() {
    BlockTypes.HAY_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHayBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HAY_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
