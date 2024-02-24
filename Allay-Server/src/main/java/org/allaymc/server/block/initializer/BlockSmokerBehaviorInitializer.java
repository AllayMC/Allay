package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSmokerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmokerBehaviorInitializer {
  static void init() {
    BlockTypes.SMOKER_TYPE = BlockTypeBuilder
            .builder(BlockSmokerBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOKER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
