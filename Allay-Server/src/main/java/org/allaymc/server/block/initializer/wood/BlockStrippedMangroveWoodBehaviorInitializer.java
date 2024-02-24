package org.allaymc.server.block.initializer.wood;

import org.allaymc.api.block.interfaces.wood.BlockStrippedMangroveWoodBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedMangroveWoodBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_MANGROVE_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockStrippedMangroveWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
