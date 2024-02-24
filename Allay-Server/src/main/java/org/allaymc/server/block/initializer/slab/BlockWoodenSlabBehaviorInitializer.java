package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockWoodenSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWoodenSlabBehaviorInitializer {
  static void init() {
    BlockTypes.WOODEN_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWoodenSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WOODEN_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
  }
}
