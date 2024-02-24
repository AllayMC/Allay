package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSmallDripleafBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmallDripleafBlockBehaviorInitializer {
  static void init() {
    BlockTypes.SMALL_DRIPLEAF_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockSmallDripleafBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
