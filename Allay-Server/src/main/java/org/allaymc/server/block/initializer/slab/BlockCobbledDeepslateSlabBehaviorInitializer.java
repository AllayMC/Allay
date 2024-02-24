package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockCobbledDeepslateSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCobbledDeepslateSlabBehaviorInitializer {
  static void init() {
    BlockTypes.COBBLED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
