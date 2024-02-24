package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSoulCampfireBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSoulCampfireBehaviorInitializer {
  static void init() {
    BlockTypes.SOUL_CAMPFIRE_TYPE = BlockTypeBuilder
            .builder(BlockSoulCampfireBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE)
            .setProperties(VanillaBlockPropertyTypes.EXTINGUISHED, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
