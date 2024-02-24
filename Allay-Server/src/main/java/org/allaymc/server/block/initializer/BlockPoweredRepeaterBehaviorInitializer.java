package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPoweredRepeaterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPoweredRepeaterBehaviorInitializer {
  static void init() {
    BlockTypes.POWERED_REPEATER_TYPE = BlockTypeBuilder
            .builder(BlockPoweredRepeaterBehavior.class)
            .vanillaBlock(VanillaBlockId.POWERED_REPEATER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
            .build();
  }
}
