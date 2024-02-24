package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSeagrassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSeagrassBehaviorInitializer {
  static void init() {
    BlockTypes.SEAGRASS_TYPE = BlockTypeBuilder
            .builder(BlockSeagrassBehavior.class)
            .vanillaBlock(VanillaBlockId.SEAGRASS)
            .setProperties(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
            .build();
  }
}
