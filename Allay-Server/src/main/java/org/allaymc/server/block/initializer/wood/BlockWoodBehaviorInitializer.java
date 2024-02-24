package org.allaymc.server.block.initializer.wood;

import org.allaymc.api.block.interfaces.wood.BlockWoodBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWoodBehaviorInitializer {
  static void init() {
    BlockTypes.WOOD_TYPE = BlockTypeBuilder
            .builder(BlockWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
  }
}
