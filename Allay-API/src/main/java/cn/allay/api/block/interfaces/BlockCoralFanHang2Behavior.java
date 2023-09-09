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
public interface BlockCoralFanHang2Behavior extends BlockBehavior {
    BlockType<BlockCoralFanHang2Behavior> CORAL_FAN_HANG2_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang2Behavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG2)
            .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
}
