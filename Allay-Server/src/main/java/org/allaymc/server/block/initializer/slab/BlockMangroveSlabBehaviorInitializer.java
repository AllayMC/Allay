package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockMangroveSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveSlabBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockMangroveSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
