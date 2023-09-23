package cn.allay.api.block.interfaces.hopper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHopperBehavior extends BlockBehavior {
    BlockType<BlockHopperBehavior> HOPPER_TYPE = BlockTypeBuilder
            .builder(BlockHopperBehavior.class)
            .vanillaBlock(VanillaBlockId.HOPPER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
            .build();
}
