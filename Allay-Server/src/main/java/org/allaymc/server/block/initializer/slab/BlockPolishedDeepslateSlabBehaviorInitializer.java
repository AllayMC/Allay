package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockPolishedDeepslateSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedDeepslateSlabBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
