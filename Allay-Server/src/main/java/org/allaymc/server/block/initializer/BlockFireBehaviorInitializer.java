package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFireBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFireBehaviorInitializer {
  static void init() {
    BlockTypes.FIRE_TYPE = BlockTypeBuilder
            .builder(BlockFireBehavior.class)
            .vanillaBlock(VanillaBlockId.FIRE)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }
}
