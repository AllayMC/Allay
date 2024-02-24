package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockExposedCutCopperSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedCutCopperSlabBehaviorInitializer {
  static void init() {
    BlockTypes.EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockExposedCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
