package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockMudBrickDoubleSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMudBrickDoubleSlabBehaviorInitializer {
  static void init() {
    BlockTypes.MUD_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockMudBrickDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICK_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
