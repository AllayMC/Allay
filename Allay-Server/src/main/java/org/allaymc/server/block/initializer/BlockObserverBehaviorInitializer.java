package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockObserverBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockObserverBehaviorInitializer {
  static void init() {
    BlockTypes.OBSERVER_TYPE = BlockTypeBuilder
            .builder(BlockObserverBehavior.class)
            .vanillaBlock(VanillaBlockId.OBSERVER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_FACING_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
