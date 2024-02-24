package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBeetrootBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBeetrootBehaviorInitializer {
  static void init() {
    BlockTypes.BEETROOT_TYPE = BlockTypeBuilder
            .builder(BlockBeetrootBehavior.class)
            .vanillaBlock(VanillaBlockId.BEETROOT)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }
}
