package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPurpurBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpurBlockBehaviorInitializer {
  static void init() {
    BlockTypes.PURPUR_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockPurpurBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPUR_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
