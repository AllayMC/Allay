package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockDeepslateBrickDoubleSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateBrickDoubleSlabBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBrickDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
