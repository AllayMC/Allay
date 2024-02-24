package org.allaymc.server.block.initializer.coralfan;

import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanHangBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoralFanHangBehaviorInitializer {
  static void init() {
    BlockTypes.CORAL_FAN_HANG_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHangBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG)
            .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }
}
