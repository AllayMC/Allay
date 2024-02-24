package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockWaxedWeatheredDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredDoubleCutCopperSlabBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
