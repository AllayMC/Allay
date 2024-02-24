package org.allaymc.server.block.initializer.wood;

import org.allaymc.api.block.interfaces.wood.BlockCherryWoodBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryWoodBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockCherryWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
            .build();
  }
}
