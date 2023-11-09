package org.allaymc.api.block.interfaces.coralfan;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCoralFanHang3Behavior extends BlockBehavior {
  BlockType<BlockCoralFanHang3Behavior> CORAL_FAN_HANG3_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang3Behavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3)
          .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .build();
}
