package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockPolishedDeepslateDoubleSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedDeepslateDoubleSlabBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
