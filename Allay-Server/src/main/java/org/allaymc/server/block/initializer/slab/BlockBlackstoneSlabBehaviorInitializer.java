package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockBlackstoneSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackstoneSlabBehaviorInitializer {
  static void init() {
    BlockTypes.BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
