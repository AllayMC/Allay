package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockTuffSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTuffSlabBehaviorInitializer {
  static void init() {
    BlockTypes.TUFF_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockTuffSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
