package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockWeatheredCutCopperSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCutCopperSlabBehaviorInitializer {
  static void init() {
    BlockTypes.WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
