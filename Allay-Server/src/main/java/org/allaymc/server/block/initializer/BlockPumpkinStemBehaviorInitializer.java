package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPumpkinStemBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPumpkinStemBehaviorInitializer {
  static void init() {
    BlockTypes.PUMPKIN_STEM_TYPE = BlockTypeBuilder
            .builder(BlockPumpkinStemBehavior.class)
            .vanillaBlock(VanillaBlockId.PUMPKIN_STEM)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
            .build();
  }
}
