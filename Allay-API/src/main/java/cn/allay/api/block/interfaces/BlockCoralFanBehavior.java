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
public interface BlockCoralFanBehavior extends BlockBehavior {
    BlockType<BlockCoralFanBehavior> CORAL_FAN_TYPE = BlockTypeBuilder
            .builder(BlockCoralFanBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN)
            .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
            .build();
}
