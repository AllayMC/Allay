package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBellBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBellBehaviorInitializer {
  static void init() {
    BlockTypes.BELL_TYPE = BlockTypeBuilder
            .builder(BlockBellBehavior.class)
            .vanillaBlock(VanillaBlockId.BELL)
            .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
            .build();
  }
}
