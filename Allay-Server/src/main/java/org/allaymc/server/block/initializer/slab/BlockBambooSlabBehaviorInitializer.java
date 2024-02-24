package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockBambooSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooSlabBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBambooSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
