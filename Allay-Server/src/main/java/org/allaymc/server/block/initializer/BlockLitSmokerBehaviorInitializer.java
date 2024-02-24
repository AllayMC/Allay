package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLitSmokerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitSmokerBehaviorInitializer {
  static void init() {
    BlockTypes.LIT_SMOKER_TYPE = BlockTypeBuilder
            .builder(BlockLitSmokerBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_SMOKER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
