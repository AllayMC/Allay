package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockDoubleWoodenSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDoubleWoodenSlabBehaviorInitializer {
  static void init() {
    BlockTypes.DOUBLE_WOODEN_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDoubleWoodenSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_WOODEN_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
  }
}
