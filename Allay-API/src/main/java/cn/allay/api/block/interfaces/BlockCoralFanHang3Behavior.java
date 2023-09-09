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
public interface BlockCoralFanHang3Behavior extends BlockBehavior {
    BlockType<BlockCoralFanHang3Behavior> CORAL_FAN_HANG3_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang3Behavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3)
            .setProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
}
