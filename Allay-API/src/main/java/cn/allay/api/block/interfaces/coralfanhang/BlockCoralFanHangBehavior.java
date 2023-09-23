package cn.allay.api.block.interfaces.coralfanhang;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
