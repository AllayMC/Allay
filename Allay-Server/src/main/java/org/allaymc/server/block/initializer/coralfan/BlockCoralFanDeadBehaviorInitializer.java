package org.allaymc.server.block.initializer.coralfan;

import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanDeadBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoralFanDeadBehaviorInitializer {
  static void init() {
    BlockTypes.CORAL_FAN_DEAD_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanDeadBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_DEAD)
            .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
            .build();
  }
}
