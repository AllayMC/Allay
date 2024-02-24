package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAnvilBehaviorInitializer {
  static void init() {
    BlockTypes.ANVIL_TYPE = BlockTypeBuilder
            .builder(BlockAnvilBehavior.class)
            .vanillaBlock(VanillaBlockId.ANVIL)
            .setProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
