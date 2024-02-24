package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockDoubleCutCopperSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDoubleCutCopperSlabBehaviorInitializer {
  static void init() {
    BlockTypes.DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDoubleCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
