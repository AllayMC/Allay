package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockCherryDoubleSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryDoubleSlabBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCherryDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
