package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
