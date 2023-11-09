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
public interface BlockCoralFanDeadBehavior extends BlockBehavior {
  BlockType<BlockCoralFanDeadBehavior> CORAL_FAN_DEAD_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanDeadBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_DEAD)
          .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
          .build();
}
