package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockCobbledDeepslateDoubleSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCobbledDeepslateDoubleSlabBehaviorInitializer {
  static void init() {
    BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
