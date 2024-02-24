package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEnderChestBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEnderChestBehaviorInitializer {
  static void init() {
    BlockTypes.ENDER_CHEST_TYPE = BlockTypeBuilder
            .builder(BlockEnderChestBehavior.class)
            .vanillaBlock(VanillaBlockId.ENDER_CHEST)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
