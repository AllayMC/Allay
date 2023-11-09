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
public interface BlockCoralFanHangBehavior extends BlockBehavior {
  BlockType<BlockCoralFanHangBehavior> CORAL_FAN_HANG_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHangBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG)
          .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
          .build();
}
