package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTwistingVinesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTwistingVinesBehaviorInitializer {
  static void init() {
    BlockTypes.TWISTING_VINES_TYPE = BlockTypeBuilder
            .builder(BlockTwistingVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.TWISTING_VINES)
            .setProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
            .build();
  }
}
