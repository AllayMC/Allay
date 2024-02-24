package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockCherrySlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherrySlabBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCherrySlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
