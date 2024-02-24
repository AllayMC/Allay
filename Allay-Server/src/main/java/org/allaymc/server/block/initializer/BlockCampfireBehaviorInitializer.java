package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCampfireBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCampfireBehaviorInitializer {
  static void init() {
    BlockTypes.CAMPFIRE_TYPE = BlockTypeBuilder
            .builder(BlockCampfireBehavior.class)
            .vanillaBlock(VanillaBlockId.CAMPFIRE)
            .setProperties(VanillaBlockPropertyTypes.EXTINGUISHED, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
