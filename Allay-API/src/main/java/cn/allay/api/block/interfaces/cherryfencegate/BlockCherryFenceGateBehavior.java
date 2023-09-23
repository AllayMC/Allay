package cn.allay.api.block.interfaces.cherryfencegate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCherryFenceGateBehavior extends BlockBehavior {
    BlockType<BlockCherryFenceGateBehavior> CHERRY_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockCherryFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
}
