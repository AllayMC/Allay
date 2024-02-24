package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStonecutterBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStonecutterBlockBehaviorInitializer {
  static void init() {
    BlockTypes.STONECUTTER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStonecutterBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STONECUTTER_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
