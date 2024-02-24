package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockQuartzBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockQuartzBlockBehaviorInitializer {
  static void init() {
    BlockTypes.QUARTZ_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockQuartzBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
