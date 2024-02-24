package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBedBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBedBehaviorInitializer {
  static void init() {
    BlockTypes.BED_TYPE = BlockTypeBuilder
            .builder(BlockBedBehavior.class)
            .vanillaBlock(VanillaBlockId.BED)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HEAD_PIECE_BIT, VanillaBlockPropertyTypes.OCCUPIED_BIT)
            .build();
  }
}
