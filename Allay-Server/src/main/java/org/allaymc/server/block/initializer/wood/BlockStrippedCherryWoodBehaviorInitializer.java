package org.allaymc.server.block.initializer.wood;

import org.allaymc.api.block.interfaces.wood.BlockStrippedCherryWoodBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedCherryWoodBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_CHERRY_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCherryWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
