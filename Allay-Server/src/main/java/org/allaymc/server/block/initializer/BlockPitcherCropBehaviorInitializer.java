package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPitcherCropBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPitcherCropBehaviorInitializer {
  static void init() {
    BlockTypes.PITCHER_CROP_TYPE = BlockTypeBuilder
            .builder(BlockPitcherCropBehavior.class)
            .vanillaBlock(VanillaBlockId.PITCHER_CROP)
            .setProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
