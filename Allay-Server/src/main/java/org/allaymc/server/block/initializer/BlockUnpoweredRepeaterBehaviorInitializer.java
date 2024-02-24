package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockUnpoweredRepeaterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockUnpoweredRepeaterBehaviorInitializer {
  static void init() {
    BlockTypes.UNPOWERED_REPEATER_TYPE = BlockTypeBuilder
            .builder(BlockUnpoweredRepeaterBehavior.class)
            .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
            .build();
  }
}
