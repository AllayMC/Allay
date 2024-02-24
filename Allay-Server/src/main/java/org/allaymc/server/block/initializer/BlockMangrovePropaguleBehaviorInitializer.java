package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMangrovePropaguleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangrovePropaguleBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_PROPAGULE_TYPE = BlockTypeBuilder
            .builder(BlockMangrovePropaguleBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE)
            .setProperties(VanillaBlockPropertyTypes.HANGING, VanillaBlockPropertyTypes.PROPAGULE_STAGE)
            .build();
  }
}
