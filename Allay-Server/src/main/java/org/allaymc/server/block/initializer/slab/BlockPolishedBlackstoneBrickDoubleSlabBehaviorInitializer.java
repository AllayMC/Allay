package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockPolishedBlackstoneBrickDoubleSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneBrickDoubleSlabBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
