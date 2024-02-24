package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTurtleEggBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTurtleEggBehaviorInitializer {
  static void init() {
    BlockTypes.TURTLE_EGG_TYPE = BlockTypeBuilder
            .builder(BlockTurtleEggBehavior.class)
            .vanillaBlock(VanillaBlockId.TURTLE_EGG)
            .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE, VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
            .build();
  }
}
