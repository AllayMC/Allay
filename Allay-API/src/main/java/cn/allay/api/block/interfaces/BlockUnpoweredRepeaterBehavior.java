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
public interface BlockUnpoweredRepeaterBehavior extends BlockBehavior {
    BlockType<BlockUnpoweredRepeaterBehavior> UNPOWERED_REPEATER_TYPE = BlockTypeBuilder
            .builder(BlockUnpoweredRepeaterBehavior.class)
            .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
            .build();
}
