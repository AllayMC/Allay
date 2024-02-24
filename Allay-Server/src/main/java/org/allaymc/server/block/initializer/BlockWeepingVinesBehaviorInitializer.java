package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWeepingVinesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeepingVinesBehaviorInitializer {
  static void init() {
    BlockTypes.WEEPING_VINES_TYPE = BlockTypeBuilder
            .builder(BlockWeepingVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.WEEPING_VINES)
            .setProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
            .build();
  }
}
