package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPointedDripstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPointedDripstoneBehaviorInitializer {
  static void init() {
    BlockTypes.POINTED_DRIPSTONE_TYPE = BlockTypeBuilder
            .builder(BlockPointedDripstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE)
            .setProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
