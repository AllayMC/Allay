package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockCrimsonSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonSlabBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
