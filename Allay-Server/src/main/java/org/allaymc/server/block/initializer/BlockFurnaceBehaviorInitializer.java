package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFurnaceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFurnaceBehaviorInitializer {
  static void init() {
    BlockTypes.FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
