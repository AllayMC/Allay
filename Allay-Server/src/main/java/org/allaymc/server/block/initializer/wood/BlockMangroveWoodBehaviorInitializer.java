package org.allaymc.server.block.initializer.wood;

import org.allaymc.api.block.interfaces.wood.BlockMangroveWoodBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveWoodBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockMangroveWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
            .build();
  }
}
