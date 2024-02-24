package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSoulFireBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSoulFireBehaviorInitializer {
  static void init() {
    BlockTypes.SOUL_FIRE_TYPE = BlockTypeBuilder
            .builder(BlockSoulFireBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_FIRE)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }
}
