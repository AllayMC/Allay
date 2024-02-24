package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGrindstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrindstoneBehaviorInitializer {
  static void init() {
    BlockTypes.GRINDSTONE_TYPE = BlockTypeBuilder
            .builder(BlockGrindstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.GRINDSTONE)
            .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }
}
