package org.allaymc.server.block.initializer.coralfan;

import org.allaymc.api.block.interfaces.coralfan.BlockCoralFanHang2Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoralFanHang2BehaviorInitializer {
  static void init() {
    BlockTypes.CORAL_FAN_HANG2_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang2Behavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG2)
            .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }
}
