package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneSlabBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
