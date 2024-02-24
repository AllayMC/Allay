package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockUnpoweredComparatorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockUnpoweredComparatorBehaviorInitializer {
  static void init() {
    BlockTypes.UNPOWERED_COMPARATOR_TYPE = BlockTypeBuilder
            .builder(BlockUnpoweredComparatorBehavior.class)
            .vanillaBlock(VanillaBlockId.UNPOWERED_COMPARATOR)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
            .build();
  }
}
